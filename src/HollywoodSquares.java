import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

/**
 * Modern Hollywood Squares game using Java 21 features.
 * Main game logic with enhanced switch expressions, pattern matching, and modern APIs.
 */
public final class HollywoodSquares {
    private static final int EMPTY_CELL = 3;
    private static final int WINNING_SCORE = 500;
    private static final int WIN_ROUND_SCORE = 250;
    private static final int TIE_MULTIPLIER = 50;
    
    private final int[][] board;
    private final Player player1;
    private final Player player2;
    private final QuestionBank questionBank;
    private final ModernGameBoard gameBoard;
    
    private boolean gameWon;
    private boolean roundWon;
    private int questionCounter;
    private int currentTurn;
    private String currentAnswer;

    public HollywoodSquares() {
        this.board = new int[3][3];
        this.gameBoard = new ModernGameBoard();
        
        // Initialize board
        initializeBoard();
        
        // Setup game
        var difficulty = gameBoard.askDifficulty();
        this.questionBank = new QuestionBank(difficulty);
        
        var numPlayers = gameBoard.askNumOfPlayers();
        this.player1 = createPlayer1(numPlayers);
        this.player2 = createPlayer2(numPlayers);
        
        gameBoard.setPlayers(player1.getName(), player2.getName());
        
        this.gameWon = false;
        this.roundWon = false;
        this.questionCounter = 0;
        this.currentTurn = 1;
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    private Player createPlayer1(int numPlayers) {
        String name = null;
        while (name == null || name.isBlank()) {
            name = JOptionPane.showInputDialog("Please enter your name.");
        }
        return new Player(name, 0, true);
    }

    private Player createPlayer2(int numPlayers) {
        if (numPlayers == 1) {
            return new Player("Computer", 1, false);
        }
        var name = JOptionPane.showInputDialog("Please enter player two's name.");
        return new Player(name != null ? name : "Player 2", 1, true);
    }

    public void play() {
        while (!gameWon) {
            playTurn();
        }
        endGame();
    }

    private void playTurn() {
        var currentPlayer = getCurrentPlayer();
        var block = selectBlock(currentPlayer);
        
        if (!isBlockAvailable(block)) {
            gameBoard.display("This block is not available\\n");
            return;
        }
        
        displayBlockSelection(block);
        var question = askQuestion();
        displayCelebAnswer(question);
        
        var playerDecision = getPlayerDecision(currentPlayer);
        var isCorrect = checkAnswer(question, playerDecision);
        
        handleAnswer(isCorrect, currentPlayer);
        
        if (roundWon) {
            handleRoundWin();
        }
        
        switchTurn();
    }

    private Player getCurrentPlayer() {
        return currentTurn == 1 ? player1 : player2;
    }

    private Player getOpponentPlayer() {
        return currentTurn == 1 ? player2 : player1;
    }

    private int selectBlock(Player player) {
        gameBoard.display("%s's turn\\n".formatted(player.getName()));
        JOptionPane.showMessageDialog(null, "%s's turn.".formatted(player.getName()));
        
        if (player.isHuman()) {
            int block;
            do {
                block = gameBoard.getBlock();
            } while (block == 0);
            return block;
        }
        
        return selectComputerBlock();
    }

    private int selectComputerBlock() {
        // AI logic: Try to win, block opponent, or take strategic position
        var winningBlock = findWinningBlock(player2.getMark());
        if (winningBlock != -1) return winningBlock;
        
        var blockingBlock = findWinningBlock(player1.getMark());
        if (blockingBlock != -1) return blockingBlock;
        
        // Take center if available
        if (isBlockAvailable(5)) return 5;
        
        // Take corners or edges
        int[] preferredBlocks = {1, 3, 7, 9, 2, 4, 6, 8};
        for (var block : preferredBlocks) {
            if (isBlockAvailable(block)) return block;
        }
        
        return 5; // Fallback
    }

    private int findWinningBlock(int mark) {
        for (int block = 1; block <= 9; block++) {
            if (!isBlockAvailable(block)) continue;
            
            var pos = Position.fromBlock(block);
            board[pos.row()][pos.column()] = mark;
            
            if (checkWinningPatternAt(pos, mark)) {
                board[pos.row()][pos.column()] = EMPTY_CELL;
                return block;
            }
            
            board[pos.row()][pos.column()] = EMPTY_CELL;
        }
        return -1;
    }

    private boolean isBlockAvailable(int block) {
        var pos = Position.fromBlock(block);
        return board[pos.row()][pos.column()] == EMPTY_CELL;
    }

    private void displayBlockSelection(int block) {
        var celebrity = Celebrity.fromBlock(block);
        gameBoard.display("Player %d has selected: %s\\n".formatted(currentTurn, celebrity.getDisplayName()));
    }

    private Question askQuestion() {
        var question = questionBank.getQuestion(questionCounter);
        gameBoard.displayQuestion(question.question());
        return question;
    }

    private void displayCelebAnswer(Question question) {
        currentAnswer = questionBank.getRandomAnswer(question);
        gameBoard.displayAnswer(currentAnswer);
        
        questionCounter = (questionCounter + 1) % questionBank.getQuestionCount();
    }

    private boolean getPlayerDecision(Player player) {
        if (player.isHuman()) {
            return gameBoard.agreeDisagree();
        }
        
        // Computer random decision
        var decision = ThreadLocalRandom.current().nextBoolean();
        var action = decision ? "agreed" : "disagreed";
        gameBoard.display("%s has %s.\\n".formatted(player.getName(), action));
        return decision;
    }

    private boolean checkAnswer(Question question, boolean playerAgreed) {
        var isAnswerCorrect = questionBank.isCorrectAnswer(question, currentAnswer);
        return (playerAgreed && isAnswerCorrect) || (!playerAgreed && !isAnswerCorrect);
    }

    private void handleAnswer(boolean isCorrect, Player currentPlayer) {
        if (isCorrect) {
            gameBoard.display("Your answer is correct!\\n");
            markBlock(currentPlayer);
        } else {
            gameBoard.display("Your answer is not correct!\\n");
            var opponent = getOpponentPlayer();
            
            if (!wouldOpponentWin(opponent)) {
                markBlock(opponent);
            }
        }
    }

    private boolean wouldOpponentWin(Player opponent) {
        return opponent.getNumOfMarks() >= 2; // Simplified check
    }

    private void markBlock(Player player) {
        var block = gameBoard.getBlock();
        var pos = Position.fromBlock(block);
        
        board[pos.row()][pos.column()] = player.getMark();
        player.incrementMarks();
        
        gameBoard.updateBoardAtBlock(block, player.getMark());
        
        checkRoundWin(pos, player);
    }

    private void checkRoundWin(Position pos, Player player) {
        var totalMarks = player1.getNumOfMarks() + player2.getNumOfMarks();
        
        if (totalMarks == 9) {
            handleTieGame();
            return;
        }
        
        if (checkWinningPatternAt(pos, player.getMark())) {
            roundWon = true;
            player.updateScore(WIN_ROUND_SCORE);
            gameBoard.updateScore(player.getMark(), WIN_ROUND_SCORE);
        }
    }

    private boolean checkWinningPatternAt(Position pos, int mark) {
        var row = pos.row();
        var col = pos.column();
        
        // Check row
        if (board[row][0] == mark && board[row][1] == mark && board[row][2] == mark) {
            return true;
        }
        
        // Check column
        if (board[0][col] == mark && board[1][col] == mark && board[2][col] == mark) {
            return true;
        }
        
        // Check diagonals
        if (row == col && board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
            return true;
        }
        
        if (row + col == 2 && board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
            return true;
        }
        
        return false;
    }

    private void handleTieGame() {
        var winner = player1.getNumOfMarks() > player2.getNumOfMarks() ? player1 : player2;
        var score = winner.getNumOfMarks() * TIE_MULTIPLIER;
        winner.updateScore(score);
        roundWon = true;
    }

    private void handleRoundWin() {
        checkGameWin();
        resetBoard();
        gameBoard.display("New Round.\\n");
        currentTurn = 2;
    }

    private void checkGameWin() {
        if (player1.getScore() >= WINNING_SCORE || player2.getScore() >= WINNING_SCORE) {
            gameWon = true;
        }
    }

    private void resetBoard() {
        if (!gameWon) {
            initializeBoard();
            gameBoard.cleanBoard();
            player1.resetMarks();
            player2.resetMarks();
            roundWon = false;
        }
    }

    private void switchTurn() {
        currentTurn = currentTurn == 1 ? 2 : 1;
    }

    private void endGame() {
        var winner = player1.getScore() > player2.getScore() ? player1 : player2;
        var message = """
            %s's score: %d
            %s's score: %d
            The winner is %s
            """.formatted(
                player1.getName(), player1.getScore(),
                player2.getName(), player2.getScore(),
                winner.getName()
            );
        
        gameBoard.display(message);
        JOptionPane.showMessageDialog(null, "*** The winner is %s ***".formatted(winner.getName()));
    }

    public static void main(String[] args) {
        var game = new HollywoodSquares();
        game.play();
    }
}

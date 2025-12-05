import java.awt.*;
import javax.swing.*;

/**
 * Modern GameBoard using Java 21 features and Swing best practices.
 * Handles all GUI rendering and user interactions.
 */
public final class GameBoard {
    private static final int FRAME_WIDTH = 1110;
    private static final int FRAME_HEIGHT = 700;
    
    // UI Components
    private final JFrame frame;
    private final JTextField questionField;
    private final JTextField answerField;
    private final JTextField player1Field;
    private final JTextField player2Field;
    private final JTextField score1Field;
    private final JTextField score2Field;
    private final JTextArea displayArea;
    private final JButton[] buttons;
    
    // Icons
    private final ImageIcon oIcon;
    private final ImageIcon xIcon;
    private final ImageIcon[] celebrityIcons;

    public GameBoard() {
        // Initialize icons
        this.oIcon = new ImageIcon("o.jpg");
        this.xIcon = new ImageIcon("x.jpg");
        this.celebrityIcons = new ImageIcon[9];
        
        for (var celebrity : Celebrity.values()) {
            celebrityIcons[celebrity.getBlock() - 1] = new ImageIcon(celebrity.getImagePath());
        }
        
        // Initialize buttons
        this.buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(celebrityIcons[i]);
        }
        
        // Initialize text fields
        this.questionField = new JTextField(20);
        this.answerField = new JTextField(20);
        this.player1Field = new JTextField(10);
        this.player2Field = new JTextField(10);
        this.score1Field = new JTextField(10);
        this.score2Field = new JTextField(10);
        this.displayArea = new JTextArea(25, 40);
        
        // Configure non-editable fields
        questionField.setEditable(false);
        answerField.setEditable(false);
        player1Field.setEditable(false);
        player2Field.setEditable(false);
        score1Field.setEditable(false);
        score2Field.setEditable(false);
        
        // Build UI
        this.frame = buildFrame();
        
        display("Welcome to Hollywood Squares, let's play!\\n");
    }

    private JFrame buildFrame() {
        var jf = new JFrame("Hollywood Squares");
        jf.setResizable(false);
        jf.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        jf.setLayout(new BorderLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Center panel with 3x3 grid
        var centerPanel = new JPanel(new GridLayout(3, 3));
        for (var button : buttons) {
            centerPanel.add(button);
        }
        
        // Right panel with player info and display
        var rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        
        var topPanel = buildTopPanel();
        rightPanel.add(topPanel, BorderLayout.NORTH);
        rightPanel.add(displayArea, BorderLayout.SOUTH);
        
        jf.add(centerPanel, BorderLayout.CENTER);
        jf.add(rightPanel, BorderLayout.EAST);
        jf.getContentPane().setBackground(Color.WHITE);
        jf.setVisible(true);
        
        return jf;
    }

    private JPanel buildTopPanel() {
        var topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        
        // Player info panel
        var playerPanel = new JPanel(new GridLayout(4, 2));
        playerPanel.setBackground(Color.WHITE);
        playerPanel.add(new JLabel("Player 1 (O)"));
        playerPanel.add(new JLabel("Player 2 (X)"));
        playerPanel.add(player1Field);
        playerPanel.add(player2Field);
        playerPanel.add(score1Field);
        playerPanel.add(score2Field);
        
        // Question/Answer panel
        var qaPanel = new JPanel(new GridLayout(4, 2));
        qaPanel.setBackground(Color.WHITE);
        qaPanel.add(new JLabel("Question"));
        qaPanel.add(questionField);
        qaPanel.add(new JLabel("Answer"));
        qaPanel.add(answerField);
        
        topPanel.add(playerPanel, BorderLayout.NORTH);
        topPanel.add(qaPanel, BorderLayout.CENTER);
        
        return topPanel;
    }

    public void setPlayers(String name1, String name2) {
        player1Field.setText(name1);
        player2Field.setText(name2);
    }

    public void updateScore(int playerMark, int score) {
        var message = switch (playerMark) {
            case 0 -> {
                score1Field.setText(String.valueOf(score));
                yield "Player 1 has won this round and gets %d points.".formatted(score);
            }
            case 1 -> {
                score2Field.setText(String.valueOf(score));
                yield "Player 2 has won this round and gets %d points.".formatted(score);
            }
            default -> "Error: Invalid player mark.";
        };
        displayArea.append(message + "\\n");
    }

    public void updateBoard(int mark) {
        var position = Position.fromBlock(mark / 100 * 3 + (mark % 100) / 10 + 1);
        var blockIndex = position.row() * 3 + position.column();
        var playerMark = mark % 10;
        
        buttons[blockIndex].setIcon(playerMark == 0 ? oIcon : xIcon);
    }

    public void updateBoardAtBlock(int block, int mark) {
        if (block < 1 || block > 9) {
            display("Error: Invalid block number\\n");
            return;
        }
        buttons[block - 1].setIcon(mark == 0 ? oIcon : xIcon);
    }

    public Difficulty askDifficulty() {
        var input = JOptionPane.showInputDialog(
            null,
            "Please enter a level of difficulty:\\nE for easy, M for medium, and H for hard."
        );
        
        if (input == null) {
            System.exit(0);
        }
        
        try {
            var difficulty = Difficulty.fromInput(input);
            display("You have selected difficulty: %s\\n".formatted(difficulty.getDisplayName()));
            return difficulty;
        } catch (IllegalArgumentException e) {
            display("Input %s is invalid.\\n".formatted(input));
            return askDifficulty(); // Recursive retry
        }
    }

    public int askNumOfPlayers() {
        Object[] options = {"One Player", "Two Players"};
        var selection = JOptionPane.showInputDialog(
            null,
            "Please select the number of players.",
            "Number of Players",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            null
        );
        
        if (selection == null) {
            System.exit(0);
        }
        
        return selection.equals(options[0]) ? 1 : 2;
    }

    public int getBlock() {
        var options = Celebrity.getDisplayNames();
        var selection = JOptionPane.showInputDialog(
            null,
            "Please select a celebrity.",
            "Choose a Celebrity",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            null
        );
        
        if (selection == null) {
            System.exit(0);
        }
        
        // Find matching celebrity
        for (var celebrity : Celebrity.values()) {
            if (celebrity.getDisplayName().equals(selection)) {
                return celebrity.getBlock();
            }
        }
        
        return 1; // Default fallback
    }

    public void displayQuestion(String question) {
        questionField.setText(question);
    }

    public void displayAnswer(String answer) {
        answerField.setText(answer);
    }

    public boolean agreeDisagree() {
        Object[] options = {"Agree", "Disagree"};
        var selection = JOptionPane.showInputDialog(
            null,
            "Please make your decision.",
            "Decision",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            null
        );
        
        if (selection == null) {
            System.exit(0);
        }
        
        return selection.equals(options[0]);
    }

    public void display(String message) {
        displayArea.append(message);
    }

    public void cleanBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setIcon(celebrityIcons[i]);
        }
        displayArea.setText("");
    }
}

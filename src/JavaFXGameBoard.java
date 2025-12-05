import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import java.util.Optional;
import java.util.List;

/**
 * Modern JavaFX UI for Hollywood Squares.
 * Features CSS styling, animations, and beautiful design.
 */
public class JavaFXGameBoard extends Application {
    private static JavaFXGameBoard instance;
    private Stage primaryStage;
    private TextArea gameLog;
    private Label score1Label;
    private Label score2Label;
    private Label player1Name;
    private Label player2Name;
    private TextField questionField;
    private TextField answerField;
    private Button[] celebrityButtons;
    private ImageView[] celebrityImages;
    
    // Store for game control
    private volatile String userInput;
    private volatile boolean waitingForInput;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        instance = this;
        this.primaryStage = stage;
        
        // Create main layout
        BorderPane root = new BorderPane();
        root.getStyleClass().add("main-container");
        
        // Header
        VBox header = createHeader();
        root.setTop(header);
        
        // Center: Game Board
        GridPane gameBoard = createGameBoard();
        root.setCenter(gameBoard);
        
        // Right: Info Panel
        VBox infoPanel = createInfoPanel();
        root.setRight(infoPanel);
        
        // Create scene with stylesheet
        Scene scene = new Scene(root, 1400, 850);
        scene.getStylesheets().add(getClass().getResource("resources/styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("🎮 Hollywood Squares - JavaFX Edition");
        stage.setResizable(true);
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();
        
        // Start game in separate thread
        new Thread(() -> {
            try {
                Thread.sleep(500); // Let UI initialize
                var game = new HollywoodSquaresJavaFX();
                game.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private VBox createHeader() {
        var header = new VBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER);
        
        var title = new Label("🎬 Hollywood Squares");
        title.getStyleClass().add("title");
        
        var subtitle = new Label("Powered by JavaFX 21 ☕ | Modern Design Edition ✨");
        subtitle.getStyleClass().add("subtitle");
        
        header.getChildren().addAll(title, subtitle);
        return header;
    }

    private GridPane createGameBoard() {
        var grid = new GridPane();
        grid.getStyleClass().add("game-board");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        
        celebrityButtons = new Button[9];
        celebrityImages = new ImageView[9];
        
        // Load celebrity images
        var celebrities = Celebrity.values();
        for (int i = 0; i < 9; i++) {
            var celebrity = celebrities[i];
            var image = new Image("file:" + celebrity.getImagePath(), 160, 160, true, true);
            celebrityImages[i] = new ImageView(image);
            
            var button = new Button();
            button.setGraphic(celebrityImages[i]);
            button.getStyleClass().add("celebrity-button");
            button.setMinSize(180, 180);
            button.setMaxSize(180, 180);
            
            // Add hover animation
            button.setOnMouseEntered(e -> animateScale(button, 1.05));
            button.setOnMouseExited(e -> animateScale(button, 1.0));
            
            celebrityButtons[i] = button;
            
            int row = i / 3;
            int col = i % 3;
            grid.add(button, col, row);
        }
        
        return grid;
    }

    private VBox createInfoPanel() {
        var panel = new VBox(15);
        panel.getStyleClass().add("info-panel");
        
        // Player Info Card
        var playerCard = createPlayerCard();
        
        // Question Card
        var questionCard = createQuestionCard();
        
        // Game Log Card
        var logCard = createLogCard();
        
        panel.getChildren().addAll(playerCard, questionCard, logCard);
        return panel;
    }

    private VBox createPlayerCard() {
        var card = new VBox(10);
        card.getStyleClass().add("card");
        
        var title = new Label("👥 Players");
        title.getStyleClass().add("card-title");
        
        var grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        // Player 1
        var p1Label = new Label("Player 1 ⭕");
        p1Label.getStyleClass().add("player-label");
        player1Name = new Label("...");
        player1Name.getStyleClass().add("player-name");
        score1Label = new Label("0");
        score1Label.getStyleClass().add("score-label");
        
        // Player 2
        var p2Label = new Label("Player 2 ❌");
        p2Label.getStyleClass().add("player-label");
        player2Name = new Label("...");
        player2Name.getStyleClass().add("player-name");
        score2Label = new Label("0");
        score2Label.getStyleClass().add("score-label");
        
        grid.add(p1Label, 0, 0);
        grid.add(p2Label, 1, 0);
        grid.add(player1Name, 0, 1);
        grid.add(player2Name, 1, 1);
        grid.add(score1Label, 0, 2);
        grid.add(score2Label, 1, 2);
        
        card.getChildren().addAll(title, grid);
        return card;
    }

    private VBox createQuestionCard() {
        var card = new VBox(10);
        card.getStyleClass().add("card");
        
        var title = new Label("❓ Question & Answer");
        title.getStyleClass().add("card-title");
        
        var qLabel = new Label("Question:");
        qLabel.getStyleClass().add("field-label");
        questionField = new TextField();
        questionField.setEditable(false);
        questionField.getStyleClass().add("question-field");
        
        var aLabel = new Label("Celebrity Answer:");
        aLabel.getStyleClass().add("field-label");
        answerField = new TextField();
        answerField.setEditable(false);
        answerField.getStyleClass().add("answer-field");
        
        card.getChildren().addAll(title, qLabel, questionField, aLabel, answerField);
        return card;
    }

    private VBox createLogCard() {
        var card = new VBox(10);
        card.getStyleClass().add("card");
        
        var title = new Label("📋 Game Log");
        title.getStyleClass().add("card-title");
        
        gameLog = new TextArea();
        gameLog.setEditable(false);
        gameLog.setWrapText(true);
        gameLog.setPrefRowCount(18);
        gameLog.getStyleClass().add("game-log");
        
        card.getChildren().addAll(title, gameLog);
        VBox.setVgrow(card, Priority.ALWAYS);
        return card;
    }

    // Animation helper
    private void animateScale(Button button, double scale) {
        var transition = new ScaleTransition(Duration.millis(200), button);
        transition.setToX(scale);
        transition.setToY(scale);
        transition.play();
    }

    // Public API for game control
    public static JavaFXGameBoard getInstance() {
        return instance;
    }

    public void setPlayers(String name1, String name2) {
        Platform.runLater(() -> {
            player1Name.setText(name1);
            player2Name.setText(name2);
        });
    }

    public void updateScore(int playerMark, int score) {
        Platform.runLater(() -> {
            var label = playerMark == 0 ? score1Label : score2Label;
            label.setText(String.valueOf(score));
            
            // Animate score
            animateScoreFlash(label);
            
            var message = playerMark == 0 
                ? "🎉 Player 1 won this round! +" + score + " points\n"
                : "🎉 Player 2 won this round! +" + score + " points\n";
            display(message);
        });
    }

    private void animateScoreFlash(Label label) {
        label.getStyleClass().add("flash");
        var timeline = new Timeline(
            new KeyFrame(Duration.millis(500), e -> label.getStyleClass().remove("flash"))
        );
        timeline.play();
    }

    public void updateBoardAtBlock(int block, int mark) {
        Platform.runLater(() -> {
            if (block < 1 || block > 9) return;
            
            var button = celebrityButtons[block - 1];
            var image = mark == 0 ? new Image("file:o.jpg", 160, 160, true, true)
                                  : new Image("file:x.jpg", 160, 160, true, true);
            
            // Animate transition
            var fade = new FadeTransition(Duration.millis(300), button);
            fade.setFromValue(1.0);
            fade.setToValue(0.3);
            fade.setOnFinished(e -> {
                button.setGraphic(new ImageView(image));
                button.getStyleClass().add(mark == 0 ? "player-o" : "player-x");
                var fadeIn = new FadeTransition(Duration.millis(300), button);
                fadeIn.setFromValue(0.3);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
            fade.play();
        });
    }

    public Difficulty askDifficulty() {
        var result = showChoiceDialog(
            "Select Difficulty",
            "Choose your challenge level:",
            List.of("🟢 Easy", "🟡 Medium", "🔴 Hard"),
            "🟢 Easy"
        );
        
        return switch (result) {
            case "🟢 Easy" -> Difficulty.EASY;
            case "🟡 Medium" -> Difficulty.MEDIUM;
            case "🔴 Hard" -> Difficulty.HARD;
            default -> Difficulty.EASY;
        };
    }

    public int askNumOfPlayers() {
        var result = showChoiceDialog(
            "Number of Players",
            "How many players?",
            List.of("👤 One Player", "👥 Two Players"),
            "👤 One Player"
        );
        return result.equals("👤 One Player") ? 1 : 2;
    }

    public int getBlock() {
        var celebrities = Celebrity.getDisplayNames();
        var icons = new String[]{"🎬", "🎤", "⚾", "🎭", "🌟", "🎵", "👗", "💃", "🏛️"};
        var options = new String[9];
        for (int i = 0; i < 9; i++) {
            options[i] = icons[i] + " " + celebrities[i];
        }
        
        var result = showChoiceDialog(
            "Choose Celebrity",
            "Select a celebrity square:",
            List.of(options),
            options[0]
        );
        
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(result)) {
                return i + 1;
            }
        }
        return 1;
    }

    public void displayQuestion(String question) {
        Platform.runLater(() -> questionField.setText(question));
    }

    public void displayAnswer(String answer) {
        Platform.runLater(() -> answerField.setText(answer));
    }

    public boolean agreeDisagree() {
        var result = showChoiceDialog(
            "Your Decision",
            "Do you agree with the celebrity's answer?",
            List.of("✅ Agree", "❌ Disagree"),
            "✅ Agree"
        );
        return result.equals("✅ Agree");
    }

    public void display(String message) {
        Platform.runLater(() -> {
            gameLog.appendText(message);
            gameLog.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void cleanBoard() {
        Platform.runLater(() -> {
            var celebrities = Celebrity.values();
            for (int i = 0; i < 9; i++) {
                var button = celebrityButtons[i];
                var image = new Image("file:" + celebrities[i].getImagePath(), 160, 160, true, true);
                button.setGraphic(new ImageView(image));
                button.getStyleClass().removeAll("player-o", "player-x");
            }
            gameLog.clear();
            display("🔄 New round started!\n\n");
        });
    }

    private String showChoiceDialog(String title, String header, List<String> choices, String defaultChoice) {
        var result = new String[1];
        var latch = new java.util.concurrent.CountDownLatch(1);
        
        Platform.runLater(() -> {
            var dialog = new ChoiceDialog<>(defaultChoice, choices);
            dialog.setTitle(title);
            dialog.setHeaderText(header);
            dialog.setContentText("Choose:");
            
            Optional<String> response = dialog.showAndWait();
            result[0] = response.orElse(defaultChoice);
            latch.countDown();
        });
        
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return result[0];
    }

    public String askInput(String message) {
        var result = new String[1];
        var latch = new java.util.concurrent.CountDownLatch(1);
        
        Platform.runLater(() -> {
            var dialog = new TextInputDialog();
            dialog.setTitle("Input Required");
            dialog.setHeaderText(message);
            dialog.setContentText("Enter:");
            
            Optional<String> response = dialog.showAndWait();
            result[0] = response.orElse("");
            latch.countDown();
        });
        
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return result[0];
    }

    public void showAlert(String message) {
        Platform.runLater(() -> {
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hollywood Squares");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}

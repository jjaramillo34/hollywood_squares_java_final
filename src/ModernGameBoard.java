import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.geom.*;
import java.awt.event.*;

/**
 * Modern, beautiful UI for Hollywood Squares using contemporary design principles.
 * Features: FlatLaf theme, rounded corners, gradients, animations, modern colors.
 */
public final class ModernGameBoard {
    // Modern color palette
    private static final Color PRIMARY_COLOR = new Color(99, 102, 241);      // Indigo
    private static final Color SECONDARY_COLOR = new Color(139, 92, 246);    // Purple
    private static final Color ACCENT_COLOR = new Color(236, 72, 153);       // Pink
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);       // Green
    private static final Color WARNING_COLOR = new Color(251, 146, 60);      // Orange
    private static final Color DARK_BG = new Color(17, 24, 39);              // Dark gray
    private static final Color CARD_BG = new Color(31, 41, 55);              // Card background
    private static final Color TEXT_PRIMARY = new Color(243, 244, 246);      // Light gray
    private static final Color TEXT_SECONDARY = new Color(156, 163, 175);    // Medium gray
    
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font HEADING_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font MONO_FONT = new Font("Consolas", Font.PLAIN, 13);
    
    // UI Components
    private final JFrame frame;
    private final JTextField questionField;
    private final JTextField answerField;
    private final JTextField player1Field;
    private final JTextField player2Field;
    private final JLabel score1Label;
    private final JLabel score2Label;
    private final JTextArea displayArea;
    private final ModernButton[] buttons;
    
    // Icons
    private final ImageIcon oIcon;
    private final ImageIcon xIcon;
    private final ImageIcon[] celebrityIcons;

    public ModernGameBoard() {
        // Set modern Look and Feel
        setModernLookAndFeel();
        
        // Initialize icons
        this.oIcon = new ImageIcon("o.jpg");
        this.xIcon = new ImageIcon("x.jpg");
        this.celebrityIcons = new ImageIcon[9];
        
        for (var celebrity : Celebrity.values()) {
            celebrityIcons[celebrity.getBlock() - 1] = new ImageIcon(celebrity.getImagePath());
        }
        
        // Initialize modern buttons
        this.buttons = new ModernButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new ModernButton(celebrityIcons[i]);
        }
        
        // Initialize text fields with modern styling
        this.questionField = createModernTextField(30);
        this.answerField = createModernTextField(30);
        this.player1Field = createModernTextField(15);
        this.player2Field = createModernTextField(15);
        this.score1Label = createScoreLabel();
        this.score2Label = createScoreLabel();
        this.displayArea = createModernTextArea();
        
        // Build modern UI
        this.frame = buildModernFrame();
        
        display("🎮 Welcome to Hollywood Squares - Java 21 Edition!\n");
        display("✨ Modern UI powered by FlatLaf\n\n");
    }

    private void setModernLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            
            // Customize UI properties
            UIManager.put("control", CARD_BG);
            UIManager.put("text", TEXT_PRIMARY);
            UIManager.put("nimbusBase", PRIMARY_COLOR);
            UIManager.put("nimbusFocus", ACCENT_COLOR);
            UIManager.put("nimbusBlueGrey", DARK_BG);
            UIManager.put("nimbusSelectionBackground", SECONDARY_COLOR);
            
        } catch (Exception e) {
            System.err.println("Failed to set modern look and feel: " + e.getMessage());
        }
    }

    private JTextField createModernTextField(int columns) {
        var field = new JTextField(columns);
        field.setFont(BODY_FONT);
        field.setEditable(false);
        field.setBackground(CARD_BG);
        field.setForeground(TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(PRIMARY_COLOR.darker(), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JLabel createScoreLabel() {
        var label = new JLabel("0");
        label.setFont(new Font("Segoe UI", Font.BOLD, 32));
        label.setForeground(SUCCESS_COLOR);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JTextArea createModernTextArea() {
        var area = new JTextArea(20, 35);
        area.setFont(MONO_FONT);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBackground(DARK_BG);
        area.setForeground(TEXT_PRIMARY);
        area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return area;
    }

    private JFrame buildModernFrame() {
        var jf = new JFrame("🎮 Hollywood Squares - Java 21 Edition");
        jf.setSize(1400, 850);
        jf.setLayout(new BorderLayout(15, 15));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(DARK_BG);
        
        // Header panel
        var headerPanel = createHeaderPanel();
        
        // Center panel with 3x3 grid
        var centerPanel = createGameBoardPanel();
        
        // Right panel with info
        var rightPanel = createInfoPanel();
        
        // Main container with margins
        var mainContainer = new JPanel(new BorderLayout(15, 15));
        mainContainer.setBackground(DARK_BG);
        mainContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainContainer.add(headerPanel, BorderLayout.NORTH);
        mainContainer.add(centerPanel, BorderLayout.CENTER);
        mainContainer.add(rightPanel, BorderLayout.EAST);
        
        jf.add(mainContainer);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        
        return jf;
    }

    private JPanel createHeaderPanel() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        
        var titleLabel = new JLabel("🎬 Hollywood Squares");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        var subtitleLabel = new JLabel("Powered by Java 21 ☕ | Modern UI Edition ✨");
        subtitleLabel.setFont(BODY_FONT);
        subtitleLabel.setForeground(TEXT_SECONDARY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitleLabel);
        panel.add(Box.createVerticalStrut(15));
        
        return panel;
    }

    private JPanel createGameBoardPanel() {
        var panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(PRIMARY_COLOR, 2, 15),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        for (var button : buttons) {
            panel.add(button);
        }
        
        return panel;
    }

    private JPanel createInfoPanel() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setPreferredSize(new Dimension(400, 0));
        
        // Player info card
        var playerCard = createPlayerInfoCard();
        
        // Question card
        var questionCard = createQuestionCard();
        
        // Display card
        var displayCard = createDisplayCard();
        
        panel.add(playerCard);
        panel.add(Box.createVerticalStrut(15));
        panel.add(questionCard);
        panel.add(Box.createVerticalStrut(15));
        panel.add(displayCard);
        
        return panel;
    }

    private JPanel createPlayerInfoCard() {
        var card = new ModernCard("👥 Players");
        card.setLayout(new GridLayout(4, 2, 10, 10));
        
        var p1Label = new JLabel("Player 1 (⭕)");
        var p2Label = new JLabel("Player 2 (❌)");
        p1Label.setFont(HEADING_FONT);
        p2Label.setFont(HEADING_FONT);
        p1Label.setForeground(TEXT_PRIMARY);
        p2Label.setForeground(TEXT_PRIMARY);
        
        card.add(p1Label);
        card.add(p2Label);
        card.add(player1Field);
        card.add(player2Field);
        card.add(score1Label);
        card.add(score2Label);
        
        return card;
    }

    private JPanel createQuestionCard() {
        var card = new ModernCard("❓ Question & Answer");
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        var qLabel = new JLabel("Question:");
        qLabel.setFont(HEADING_FONT);
        qLabel.setForeground(TEXT_SECONDARY);
        qLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        questionField.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        var aLabel = new JLabel("Celebrity Answer:");
        aLabel.setFont(HEADING_FONT);
        aLabel.setForeground(TEXT_SECONDARY);
        aLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        answerField.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        card.add(qLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(questionField);
        card.add(Box.createVerticalStrut(15));
        card.add(aLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(answerField);
        
        return card;
    }

    private JPanel createDisplayCard() {
        var card = new ModernCard("📋 Game Log");
        card.setLayout(new BorderLayout());
        
        var scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(null);
        scrollPane.setBackground(DARK_BG);
        scrollPane.getViewport().setBackground(DARK_BG);
        
        card.add(scrollPane, BorderLayout.CENTER);
        
        return card;
    }

    // Public API methods
    public void setPlayers(String name1, String name2) {
        player1Field.setText(name1);
        player2Field.setText(name2);
    }

    public void updateScore(int playerMark, int score) {
        var message = switch (playerMark) {
            case 0 -> {
                score1Label.setText(String.valueOf(score));
                animateScore(score1Label);
                yield "🎉 Player 1 won this round! +%d points".formatted(score);
            }
            case 1 -> {
                score2Label.setText(String.valueOf(score));
                animateScore(score2Label);
                yield "🎉 Player 2 won this round! +%d points".formatted(score);
            }
            default -> "⚠️ Error: Invalid player mark.";
        };
        display(message + "\n");
    }

    private void animateScore(JLabel label) {
        var originalColor = label.getForeground();
        label.setForeground(WARNING_COLOR);
        
        var timer = new Timer(500, e -> label.setForeground(originalColor));
        timer.setRepeats(false);
        timer.start();
    }

    public void updateBoardAtBlock(int block, int mark) {
        if (block < 1 || block > 9) {
            display("⚠️ Error: Invalid block number\n");
            return;
        }
        
        var button = buttons[block - 1];
        button.setIcon(mark == 0 ? oIcon : xIcon);
        button.setBackground(mark == 0 ? PRIMARY_COLOR : ACCENT_COLOR);
    }

    public Difficulty askDifficulty() {
        var options = new Object[]{"🟢 Easy", "🟡 Medium", "🔴 Hard"};
        var selection = showModernDialog(
            "Select Difficulty",
            "Choose your challenge level:",
            options
        );
        
        if (selection == null) System.exit(0);
        
        try {
            var difficulty = switch (selection.toString()) {
                case "🟢 Easy" -> Difficulty.EASY;
                case "🟡 Medium" -> Difficulty.MEDIUM;
                case "🔴 Hard" -> Difficulty.HARD;
                default -> throw new IllegalArgumentException("Invalid selection");
            };
            display("✅ Difficulty selected: %s\n".formatted(difficulty.getDisplayName()));
            return difficulty;
        } catch (Exception e) {
            display("⚠️ Invalid selection\n");
            return askDifficulty();
        }
    }

    public int askNumOfPlayers() {
        var options = new Object[]{"👤 One Player", "👥 Two Players"};
        var selection = showModernDialog(
            "Number of Players",
            "How many players?",
            options
        );
        
        if (selection == null) System.exit(0);
        
        return selection.equals("👤 One Player") ? 1 : 2;
    }

    public int getBlock() {
        var displayNames = Celebrity.getDisplayNames();
        var options = new String[displayNames.length];
        var icons = new String[]{"🎬", "🎤", "⚾", "🎭", "🌟", "🎵", "👗", "💃", "🏛️"};
        
        for (int i = 0; i < displayNames.length; i++) {
            options[i] = icons[i] + " " + displayNames[i];
        }
        
        var selection = showModernDialog(
            "Choose Celebrity",
            "Select a celebrity square:",
            options
        );
        
        if (selection == null) System.exit(0);
        
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(selection)) {
                return i + 1;
            }
        }
        
        return 1;
    }

    public void displayQuestion(String question) {
        questionField.setText(question);
        questionField.setForeground(WARNING_COLOR);
    }

    public void displayAnswer(String answer) {
        answerField.setText(answer);
        answerField.setForeground(SUCCESS_COLOR);
    }

    public boolean agreeDisagree() {
        var options = new Object[]{"✅ Agree", "❌ Disagree"};
        var selection = showModernDialog(
            "Your Decision",
            "Do you agree with the celebrity's answer?",
            options
        );
        
        if (selection == null) System.exit(0);
        
        return selection.equals("✅ Agree");
    }

    public void display(String message) {
        displayArea.append(message);
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }

    public void cleanBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setIcon(celebrityIcons[i]);
            buttons[i].setBackground(CARD_BG);
        }
        displayArea.setText("");
        display("🔄 New round started!\n\n");
    }

    private Object showModernDialog(String title, String message, Object[] options) {
        return JOptionPane.showInputDialog(
            frame,
            message,
            title,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
    }

    // Modern Button Component
    private static class ModernButton extends JButton {
        public ModernButton(Icon icon) {
            super(icon);
            setPreferredSize(new Dimension(180, 180));
            setBackground(CARD_BG);
            setBorder(new RoundedBorder(PRIMARY_COLOR.darker(), 2, 12));
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(PRIMARY_COLOR.darker());
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(CARD_BG);
                }
            });
        }
    }

    // Modern Card Component
    private static class ModernCard extends JPanel {
        public ModernCard(String title) {
            setLayout(new BorderLayout(10, 10));
            setBackground(CARD_BG);
            setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(PRIMARY_COLOR.darker(), 1, 12),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
            
            if (title != null && !title.isEmpty()) {
                var titleLabel = new JLabel(title);
                titleLabel.setFont(HEADING_FONT);
                titleLabel.setForeground(TEXT_PRIMARY);
                add(titleLabel, BorderLayout.NORTH);
            }
        }
    }

    // Rounded Border
    private static class RoundedBorder extends AbstractBorder {
        private final Color color;
        private final int thickness;
        private final int radius;

        public RoundedBorder(Color color, int thickness, int radius) {
            this.color = color;
            this.thickness = thickness;
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            var g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness, thickness, thickness, thickness);
        }
    }
}

/**
 * Record representing a trivia question with correct and incorrect answers.
 * Uses Java 21 record feature for immutable data classes.
 */
public record Question(
    String question,
    String correctAnswer,
    String incorrectAnswer
) {
    public Question {
        // Compact constructor with validation
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        if (correctAnswer == null || correctAnswer.isBlank()) {
            throw new IllegalArgumentException("Correct answer cannot be null or empty");
        }
        if (incorrectAnswer == null || incorrectAnswer.isBlank()) {
            throw new IllegalArgumentException("Incorrect answer cannot be null or empty");
        }
    }
}

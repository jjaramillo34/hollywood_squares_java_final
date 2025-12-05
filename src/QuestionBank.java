import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Modern QuestionBank using Java 21 features.
 * Manages trivia questions with immutable collections and records.
 */
public final class QuestionBank {
    private final List<Question> questions;
    private final Difficulty difficulty;

    public QuestionBank(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.questions = loadQuestions(difficulty);
    }

    private static List<Question> loadQuestions(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> loadEasyQuestions();
            case MEDIUM -> loadMediumQuestions();
            case HARD -> loadHardQuestions();
        };
    }

    private static List<Question> loadEasyQuestions() {
        return List.of(
            new Question("Which colors are mixed together in order to create the color green?", 
                "Yellow and blue", "Red and blue"),
            new Question("What is the capital of New York?", 
                "Albany", "Fort Nassau"),
            new Question("How many states are there in the United States of America?", 
                "50", "46"),
            new Question("Who invented the lightbulb?", 
                "Thomas Edison", "Isaac Newton"),
            new Question("Which travels faster, sound or light?", 
                "Light", "Sound"),
            new Question("If you add red, yellow, and blue light together what color light do you get?", 
                "White", "Brown"),
            new Question("What is needed in order for plants to create oxygen?", 
                "Carbon Dioxide", "Hydrogen"),
            new Question("Is 'Watch Tony run.' a complete sentence?", 
                "Yes", "No"),
            new Question("What is an echo?", 
                "Reflected sound waves", "Sound waves with changing frequencies"),
            new Question("In poetry, how many lines are in a quatrain?", 
                "4", "3"),
            new Question("Do plant and animal cells both have chloroplasts?", 
                "No", "Yes"),
            new Question("Who is the first President of the United States of America?", 
                "George Washington", "Thomas Jefferson"),
            new Question("Which of these villains is fought by Superman? The Riddler, Green Goblin, Joker, or Lex Luthor", 
                "Lex Luthor", "Riddler"),
            new Question("If you wrote down all of Santa's reindeer in alphabetical order. Who would be last?", 
                "Vixen", "Rudolph"),
            new Question("On what continent would you find the Nile River?", 
                "Africa", "South America"),
            new Question("The violin is part of which instrument family?", 
                "String", "Percussion"),
            new Question("What is another way to write they are?", 
                "They're", "Their"),
            new Question("What is the fastest animal in the world?", 
                "Cheetah", "Lion"),
            new Question("Who painted The Mona Lisa?", 
                "Leonardo Da Vinci", "Leonardo DiCaprio"),
            new Question("What body part pumps blood around the body?", 
                "Heart", "Brain")
        );
    }

    private static List<Question> loadMediumQuestions() {
        return List.of(
            new Question("What planet is most similar in size and mass to earth?", 
                "Venus", "Saturn"),
            new Question("What organ is found only in vertebrates?", 
                "Liver", "Kidney"),
            new Question("Which country won the World Cup first place in 2010?", 
                "Spain", "Germany"),
            new Question("What should you not do, according to the 10th commandment?", 
                "Covet", "Steal"),
            new Question("What was the Beatles' first hit in America?", 
                "I Want To Hold Your Hand", "Tomorrow Never Ends"),
            new Question("Who is Sherlock Holmes best friend?", 
                "John Watson", "Conan Doyle"),
            new Question("The term Lycanthropy is the magical ability to transform into which animal?", 
                "Wolf", "Werewolf"),
            new Question("In a web address, what does 'WWW' mean?", 
                "World Wide Web", "Wired World Web"),
            new Question("Which of these is not a perfect number - 6, 16 or 28?", 
                "16", "6"),
            new Question("What common garden creature has 5 pairs of hearts?", 
                "Worm", "Weasel"),
            new Question("What did the 'T' stand for in captain James T. Kirk of the Star Trek Enterprise?", 
                "Tiberius", "Tione"),
            new Question("What is Hyperopia?", 
                "Farsightedness", "Hypertension"),
            new Question("What is the '~' symbol called?", 
                "Tilde", "Tilda"),
            new Question("What is the largest animal to have ever lived on earth?", 
                "Blue Whale", "Dinosaurs"),
            new Question("What does a kleptomaniac do?", 
                "Steal", "Sleep"),
            new Question("What country does tennis come from?", 
                "France", "Greece"),
            new Question("In the Einstein's Theory of Relativity, E= mc2, what does c stand for?", 
                "Speed of Light", "Time"),
            new Question("What element has the highest melting point?", 
                "Carbon", "Hydrogen"),
            new Question("What is tequila made from?", 
                "Blue Agave", "Wheat"),
            new Question("Seventy percent of the body's sense receptors are located here.", 
                "Eyes", "Skin")
        );
    }

    private static List<Question> loadHardQuestions() {
        return List.of(
            new Question("Hagiophobia is the fear of?", 
                "Holy Items", "Bats"),
            new Question("What is the largest Caribbean Island?", 
                "Cuba", "Jamaica"),
            new Question("What were the first names of The Brothers Grimm?", 
                "Wilhelm and Jacob", "Thomas and Alex"),
            new Question("What is Dendrology the study of?", 
                "Trees", "Drugs"),
            new Question("How long does it take for light produced by the sun to reach earth?", 
                "8 minutes", "2 hours"),
            new Question("What was the last province to join the Canadian Federation?", 
                "Newfoundland", "Alberta"),
            new Question("The animal responsible for the most deaths worldwide is?", 
                "Mosquitoes", "Snakes"),
            new Question("Which country won the World Cup second place in 2006?", 
                "France", "Italy"),
            new Question("Aldosterone is produced by?", 
                "Adrenal gland", "Heart"),
            new Question("Which vitamin cures scurvy?", 
                "Vitamin C", "Vitamin B"),
            new Question("What country was the first to give women the right to vote?", 
                "New Zealand", "United States of America"),
            new Question("Acetylsalicylic acid is better known as?", 
                "Aspirin", "Steroids"),
            new Question("What baseball player holds the record for most games in a career?", 
                "Pete Rose", "Daniel Hemmingway"),
            new Question("The only U.S. bachelor president was?", 
                "James Buchanan", "John F Kennedy"),
            new Question("Where would you find the Hyoid bone?", 
                "Throat", "Feet"),
            new Question("In what sport would you use the term daily double?", 
                "Horseracing", "Bowling"),
            new Question("What is the world's smallest lizard?", 
                "Gecko", "Salamander"),
            new Question("Where would you spend a forint(currency)?", 
                "Hungary", "Finland"),
            new Question("Where does the Hopak dance come from?", 
                "Ukraine", "Greece"),
            new Question("What is the worlds smallest island country?", 
                "Nauru", "Letos")
        );
    }

    public Question getQuestion(int index) {
        if (index < 0 || index >= questions.size()) {
            throw new IndexOutOfBoundsException("Invalid question index: " + index);
        }
        return questions.get(index);
    }

    public String getRandomAnswer(Question question) {
        // Randomly return correct or incorrect answer
        return ThreadLocalRandom.current().nextBoolean() 
            ? question.correctAnswer() 
            : question.incorrectAnswer();
    }

    public boolean isCorrectAnswer(Question question, String givenAnswer) {
        return question.correctAnswer().equals(givenAnswer);
    }

    public int getQuestionCount() {
        return questions.size();
    }
}

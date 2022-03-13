
import java.util.Random; 
 
public class QuestionAnswer 
{
	private static String[][] easy = new String [3][20];
	private static String[][] medium = new String [3][20];
	private static String[][] hard = new String [3][20];
	private int difficulty;
	private boolean isRightAnswer;

	public QuestionAnswer(int d)
	{
		difficulty = d;
		
		if(difficulty == 1)
		{
			easy[0][0] = "Which colors are mixed together in order to create the color green?";
			easy[1][0] = "Yellow and blue";
			easy[2][0] = "Red and blue";

			easy[0][1] = "What is the capital of New York?";
			easy[1][1] = "Albany";
			easy[2][1] = "Fort Nassau";
			
			easy[0][2] = "How many states are there in the United States of America?";
			easy[1][2] = "50";
			easy[2][2] = "46";
			
			easy[0][3] = "Who invented the lighbulb?";
			easy[1][3] = "Thomas Edison";
			easy[2][3] = "Isaac Newton";
			
			easy[0][4] = "Which travels faster, sound or light?";
			easy[1][4] = "Light";
			easy[2][4] = "Sound";
			
			easy[0][5] = "If you add red, yellow, and blue light together what color light do you get?";
			easy[1][5] = "White";
			easy[2][5] = "Brown";
			
			easy[0][6] = "What is needed in order for plants to create oxygen?";
			easy[1][6] = "Carbon Dioxide";
			easy[2][6] = "Hydrogen";
			
			easy[0][7] = "Is 'Watch Tony run.' a complete sentence?";
			easy[1][7] = "Yes";
			easy[2][7] = "No";
			
			easy[0][8] = "What is an echo?";
			easy[1][8] = "Reflected sound waves";
			easy[2][8] = "Sound waves with changing frequencies";
			
			easy[0][9] = "In poetry, how many lines are in a quatrain?";
			easy[1][9] = "4";
			easy[2][9] = "3";
			
			easy[0][10] = "Do plant and animal cells both have chloroplasts?";
			easy[1][10] = "No";
			easy[2][10] = "Yes";
			
			easy[0][11] = "Who is the first President of the United States of America? ";
			easy[1][11] = "George Washington";
			easy[2][11] = "this wrong";
			
			easy[0][12] = "Which of these villains is fought by Superman? The Riddler, Green Goblin, Joker, or Lex Luthor";
			easy[1][12] = "Lex Luthor";
			easy[2][12] = "Riddler";
			
			easy[0][13] = "If you wrote down all of Santa's reindeer in alphabetical order. Who would be last?";
			easy[1][13] = "Vixen";
			easy[2][13] = "Rudolph";
			
			easy[0][14] = "On what continent would you find the Nile River?";
			easy[1][14] = "Africa";
			easy[2][14] = "South America";
			
			easy[0][15] = "The violin is part of which instrument family?";
			easy[1][15] = "String";
			easy[2][15] = "Percussion";
			
			easy[0][16] = "What is another way to write they are?";
			easy[1][16] = "They're";
			easy[2][16] = "Their";
			
			easy[0][17] = "What is the fastest animal in the world?";
			easy[1][17] = "Cheetah";
			easy[2][17] = "Lion";
			
			easy[0][18] = "Who painted The Mona Lisa?";
			easy[1][18] = "Leonardo Da Vinci";
			easy[2][18] = "Leonardo DiCaprio";
			
			easy[0][19] = "What body part pumps blood around the body?";
			easy[1][19] = "Heart";
			easy[2][19] = "Brain";
		}
		else if(difficulty == 2)
		{		
			medium[0][0] = "What planet is most similar in size and mass to earth?";
			medium[1][0] = "Venus";
			medium[2][0] = "Saturn";
			
			medium[0][1] = "What organ is found only in vertebrates?";
			medium[1][1] = "Liver";
			medium[2][1] = "Kidney";
			
			medium[0][2] = "Which country won the World Cup first place in 2010?";
			medium[1][2] = "Spain";
			medium[2][2] = "Germany";
			
			medium[0][3] = "What should you not do, according to the 10th commandment?";
			medium[1][3] = "Covet";
			medium[2][3] = "Steal";
			
			medium[0][4] = "What was the Beatles' first hit in America?";
			medium[1][4] = "I Want To Hold Your Hand";
			medium[2][4] = "Tomorrow Never Ends";
			
			medium[0][5] = "Who is Sherlock Holmes best friend?";
			medium[1][5] = "John Watson";
			medium[2][5] = "Conan Doyle";
			
			medium[0][6] = "The term Lycanthropy is the magical ability to transform into which animal?";
			medium[1][6]= "Wolf";
			medium[2][6] = "Werewolf";
			
			medium[0][7] = "In a web address, what does 'WWW' mean?";
			medium[1][7] = "World Wide Web";
			medium[2][7] = "Wired World Web";
			
			medium[0][8] = "Which of these is not a perfect number - 6, 16 or 28?";
			medium[1][8] = "16";
			medium[2][8] = "6";
			
			medium[0][9] = "What common garden creature has 5 pairs of hearts?";
			medium[1][9] = "Worm";
			medium[2][9] = "Weasel";
			
			medium[0][10] = "What did the 'T' stand for in captain James T. Kirk of the Star Trek Enterprise?";
			medium[1][10] = "Tiberius";
			medium[2][10] = "Tione";
			
			medium[0][11] = "What is Hyperopia?";
			medium[1][11] = "Farsightedness";
			medium[2][11] = "Hypertension";
			
			medium[0][12] = "What is the '~' symbol called?";
			medium[1][12] = "Tilde";
			medium[2][12] = "Tilda";
			
			medium[0][13] = "What is the largest animal to have ever lived on earth?";
			medium[1][13] = "Blue Whale";
			medium[2][13] = "Dinosaurs";
			
			medium[0][14] = "What does a kleptomaniac do?";
			medium[1][14] = "Steal";
			medium[2][14] = "Sleep";
			
			medium[0][15] = "What country does tennis come from?";
			medium[1][15] = "France";
			medium[2][15] = "Greece";
			
			medium[0][16] = "In the Einstein's Theory of Relativity, E= mc2, what does c stand for?";
			medium[1][16] = "Speed of Light";
			medium[2][16] = "Time";
			
			medium[0][17] = "What element has the highest melting point?";
			medium[1][17] = "Carbon";
			medium[2][17] = "Hydrogen";
			
			medium[0][18] = "What is tequila made from??";
			medium[1][18] = "Blue Agave";
			medium[2][18] = "Wheat";
			
			medium[0][19] = "Seventy percent of the body's sense receptors are located here.";
			medium[1][19] = "Eyes";
			medium[2][19] = "Skin";
		}
		else if(difficulty == 3)
		{
			hard[0][0] = "Hagiophobia is the fear of?";
			hard[1][0] = "Holy Items";
			hard[2][0] = "Bats";
			
			hard[0][1] = "What is the largest Caribbean Island?";
			hard[1][1] = "Cuba";
			hard[2][1] = "Jamaica";
			
			hard[0][2] = "What were the first names of The Brothers Grimm?";
			hard[1][2] = "Wilhelm and Jacob";
			hard[2][2] = "Thomas and Alex";
			
			hard[0][3] = "What is Dendrology the study of?";
			hard[1][3] = "Trees";
			hard[2][3] = "Drugs";
			
			hard[0][4] = "How long does it take for light produced by the sun to reach earth?";
			hard[1][4] = "6 minutes";
			hard[2][4] = "2 hours";
			
			hard[0][5] = "What was the last province to join the Canadian Federation?";
			hard[1][5] = "Newfoundland";
			hard[2][5] = "Alberta";
			
			hard[0][6] = "The animal responsible for the most deaths worldwide is?";
			hard[1][6] = "Mosquitoes";
			hard[2][6] = "Snakes";
			
			hard[0][7] = "Which country won the World Cup second place in 2006?";
			hard[1][7] = "France";
			hard[2][7] = "Italy";
			
			hard[0][8] = "Aldosterone is produced by?";
			hard[1][8] = "Adrenal gland";
			hard[2][8] = "Heart";
			
			hard[0][9] = "Which vitamin cures scurvy?";
			hard[1][9] = "Vitamin C";
			hard[2][9] = "Vitamin B";
			
			hard[0][10] = "What country was the first to give women the right to vote?";
			hard[1][10] = "New Zealond";
			hard[2][10] = "United States of America";
			
			hard[0][11] = "Acetylsalicylic acid is better known as?";
			hard[1][11] = "Aspirin";
			hard[2][11] = "Steroids";
			
			hard[0][12] = "What baseball player holds the record for most games in a career?";
			hard[1][12] = "Pete Rose";
			hard[2][12] = "Daniel Hemmingway";
			
			hard[0][13] = "The only U.S. bachelor president was?";
			hard[1][13] = "James Buchanan";
			hard[2][13] = "John F Kennedy";
			
			hard[0][14] = "Where would you find the Hyoid bone?";
			hard[1][14] = "Throat";
			hard[2][14] = "Feet";
			
			hard[0][15] = "In what sport would you use the term daily double?";
			hard[1][15] = "Horseracing";
			hard[2][15] = "Bowling";
			
			hard[0][16] = "What is the world's smallest lizard?";
			hard[1][16] = "Gecko";
			hard[2][16] = "Salamander";
			
			hard[0][17] = "Where would you spend a forint(currency)?";
			hard[1][17] = "Hungary";
			hard[2][17] = "Finland";
			
			hard[0][18] = "Where does the Hopak dance come from?";
			hard[1][18] = "Ukraine";
			hard[2][18] = "Greece";
			
			hard[0][19] = "What is the worlds smallest island country?";
			hard[1][19] = "Nauru";
			hard[2][19] = "Letos";
		}
	}
	
	public String getQuestion(int c)
	{			
		switch(difficulty)
		{
		case 1:
			return easy[0][c];
		case 2:
			return medium [0][c];
		case 3:
			return hard [0][c];
		default:
			return "an error has ocurred";
		}
	}
	
	public String getAnswer(int c)
	{
		Random rand = new Random();
		int r = rand.nextInt(2);
		r += 1;	
		if(r == 1)
			isRightAnswer = true;
		else
			isRightAnswer = false;
	//	System.out.println("ANS is: " + isRightAnswer);
		
		switch(difficulty)
		{
		case 1:
			return easy[r][c];
		case 2:
			return medium [r][c];
		case 3:
			return hard [r][c];
		default:
			return "an error has ocurred";
		}
	}
	public boolean getIsRightAns()
	{
		return isRightAnswer;
	}
				
}


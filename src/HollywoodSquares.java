import java.util.Random;
import java.io.IOException;

import javax.swing.JOptionPane;

public class HollywoodSquares 
{
	private static int [][]board = new int [3][3];
	private static int row;
    private static int column;
    private static int block;
    private static Player p1;
    private static Player p2;
    private static QuestionAnswer qa;
    private static GameBoard gb;
    private static boolean winGame = false;
    private static boolean winRound = false;
    private static int questionCounter = 0;
    private static String tempQ;
    private static String tempA;
    private static String winner;
    private static int turn = 1;
    private static boolean isOppWin = false;
    private static boolean isCorrect;
    private static boolean decision;
    
    public static void main(String [] args)
    {
    	board [0][0] = 3;
    	board [0][1] = 3;
    	board [0][2] = 3;
    	board [1][0] = 3;
    	board [1][1] = 3;
    	board [1][2] = 3;
    	board [2][0] = 3;
    	board [2][1] = 3;
    	board [2][2] = 3;
    	gb = new GameBoard();
    	setDifficulty();
    	setPlayerNum();
    	
    	while(!winGame)
    	{    		
    		selectBlock();
    		if(isBlockAvailable())
    		{
    			if(turn == 1)
    				gb.display("Player 1 has selected: ");
    			else
    				gb.display("Player 2 has selected: ");
    			
				switch(block)
				{
				case 1:
					gb.display("Tom Cruise\n");
					break;
				case 2:
					gb.display("Mariah Cary\n");
					break;
				case 3:
					gb.display("Derek Jeter\n");
					break;
				case 4:
					gb.display("Brad Pitt\n");
					break;
				case 5:
					gb.display("Angelina Jolie\n");
					break;
				case 6:
					gb.display("Rihanna\n");
					break;
				case 7:
					gb.display("Lady Gaga\n");
					break;
				case 8:
					gb.display("Cameron Diaz\n");
					break;
				case 9:
					gb.display("Barack Obama\n");
					break;
				}
    			
    			askQuestion();
    			celebAnswer();
    			if(questionCounter == 19)
    				questionCounter = 0;
    			else
    				questionCounter++;
    			setDecision(); 			
    			checkAnswer();
    			if(isCorrect)
    			{
    				gb.display("Your answer is correct!\n");
    				switch(turn)
    				{
    				case 1:
    					markBlock(p1);
    					break;
    				case 2:
    					markBlock(p2);
    					break;
    				}
    			}
    			else if (!isCorrect)
    	    	{
    				gb.display("Your answer is not correct!\n");
    				if(turn ==1)
        				checkOppWin(p2);
        			else
        				checkOppWin(p1);
    				
    				if(!isOppWin)
    				{
        	    		switch(turn)
        				{
	    				case 1:
	    					markBlock(p2);
	    					break;
	    				case 2:
	    					markBlock(p1);
	    					break;
        				}
    				}
    				
    	    	}
    			if(winRound)
    			{
    				checkWinGame();
    				resetBoard();
    				gb.display("New Round.\n");
    				turn = 2;
    			}
    			changeTurn();
    		}
        	//this else can go away
    		else
        	{
        		gb.display("This block is not available\n");
        	}
    	}//END WHILE(!WINROUND)
    	gb.display(p1.getName()+"'s score: "+p1.getScore()+"\n");
    	gb.display(p2.getName()+"'s score: "+p2.getScore()+"\n");
    	gb.display("The winner is "+winner+"\n");
    	JOptionPane.showMessageDialog(null, "*** The winnder is "+winner+" ***");
    }//END MAIN
   

	public static void setDifficulty()
    {
    	int difficulty;
    	int temp= gb.askDifficulty();
    	while(temp == 0)
    	{
    		temp = gb.askDifficulty();
    	}
    	difficulty = temp;
    	qa = new QuestionAnswer(difficulty);
    }
    
    public static void setPlayerNum()
    {
    	int numOfPlayers = gb.askNumOfPlayers();
    	
    	if (numOfPlayers == 1)
		{
    		String n = null;
    		while(n == null)
    		{
    			n = JOptionPane.showInputDialog("Please enter your name.");
    		}
			p1 = new Player(n , 0, true);
			p2 = new Player ("Mike", 1, false);
		}
		else
		{
			String n = JOptionPane.showInputDialog("Please enter player one's name.");
			p1 = new Player(n , 0, true);
			String nn = JOptionPane.showInputDialog("Please enter player two's name.");
			p2 = new Player(nn , 1, true);
		}
    	gb.setPlayers(p1.getName(), p2.getName());
    }
    public static void selectBlock()
    {
    	if (turn ==1)
    	{
    		gb.display(p1.getName() + "'s turn\n");
    		JOptionPane.showMessageDialog(null, p1.getName()+"'s turn.");
    	}
    	else
    	{
    		gb.display(p2.getName() + "'s turn\n");
    		JOptionPane.showMessageDialog(null, p2.getName()+"'s turn.");
    	}
    	if ((turn == 1) || (turn ==2 && p2.getIsHuman()) )
    	{	int temp = 	gb.getBlock();
    		while(temp == 0)
    			temp = gb.getBlock();	
    		block = temp;
    	}
    	else //computer simulating player
    	{
    		int b1 = board[0][0];
			int b2 = board[0][1];
			int b3 = board[0][2];
			int b4 = board[1][0];
			int b5 = board[1][1];
			int b6 = board[1][2];
			int b7 = board[2][0];
			int b8 = board[2][1];
			int b9 = board[2][2];
			
			if(b1==3 &&(b2==1&&b3==1||b4==1&&b7==1||b5==1&&b9==1))
				block = 1;
			else if(b2==3 &&(b1==1&&b3==1||b5==1&&b8==1))
				block = 2;
			else if(b3==3 &&(b1==1&&b2==1||b5==1&&b7==1||b6==1&&b9==1))
				block = 3;
			else if(b4==3 &&(b1==1&&b7==1||b5==1&&b6==1))
				block = 4;
			else if(b5==3 &&(b1==1&&b9==1||b3==1&&b7==1||b4==1&&b6==1||b2==1&&b8==1))
				block = 5;
			else if(b6==3 &&(b3==1&&b9==1||b4==1&&b5==1))
				block = 6;
			else if(b7==3 &&(b1==1&&b4==1||b3==1&&b5==1||b8==1&&b9==1))
				block = 7;
			else if(b8==3 &&(b2==1&&b5==1||b7==1&&b9==1))
				block = 8;
			else if(b9==3 &&(b1==1&&b5==1||b3==3&&b6==1||b7==1&&b8==1))
				block = 9;
			else if(b1==3 &&(b2==0&&b3==0||b4==0&&b7==0||b5==0&&b9==0))
				block = 1;
			else if(b2==3 &&(b1==0&&b3==0||b5==0&&b8==0))
				block = 2;
			else if(b3==3 &&(b1==0&&b2==0||b5==0&&b7==0||b6==1&&b9==0))
				block = 3;
			else if(b4==3 &&(b1==0&&b7==0||b5==0&&b6==0))
				block = 4;
			else if(b5==3 &&(b1==0&&b9==0||b3==0&&b7==0||b4==0&&b6==0||b2==0&&b8==0))
				block = 5;
			else if(b6==3 &&(b3==0&&b9==0||b4==0&&b5==0))
				block = 6;
			else if(b7==3 &&(b1==0&&b4==0||b3==0&&b5==0||b8==0&&b9==0))
				block = 7;
			else if(b8==3 &&(b2==0&&b5==0||b7==0&&b9==0))
				block = 8;
			else if(b9==3 &&(b1==0&&b5==0||b3==0&&b6==0||b7==0&&b8==0))
				block = 9;
			else if(b5==3)
				block = 5;
			else if(b2==3)
				block = 2;
			else if(b4==3)
				block = 4;
			else if(b6==3)
				block = 6;
			else if(b8==3)
				block = 8;
			else if(b1==3)
				block = 1;
			else if(b3==3)
				block = 3;
			else if(b7==3)
				block =7;
			else if(b9==3)
				block = 9;
    	}
    	
    	if(block == 1)
		{
			row = 0;
			column = 0;
		}
		else if(block == 2)
		{
			row = 0;
			column = 1;
		}
		else if(block == 3)
		{
			row = 0;
			column = 2;
		}
		else if(block == 4)
		{
			row = 1;
			column = 0;
		}
		else if(block == 5)
		{
			row = 1;
			column = 1;
		}
		else if(block == 6)
		{
			row = 1;
			column = 2;
		}
		else if(block == 7)
		{
			row = 2;
			column = 0;
		}
		else if(block == 8)
		{
			row = 2;
			column = 1;
		}
		else if(block == 9)
		{
			row = 2;
			column = 2;
		}
    }
 
    public static boolean isBlockAvailable()
    {
    	if((board[row][column])!= 0 &&(board[row][column])!= 1)
    		return true;
    	else
    		return false;
    }
    public static void askQuestion()
    {
    	tempQ = qa.getQuestion(questionCounter);
		gb.displayQuestion(tempQ);
    }
    public static void celebAnswer()
    {
    	tempA = qa.getAnswer(questionCounter);
		gb.displayAnswer(tempA);
    }
    public static void setDecision()
    {
    	if(turn ==1 || (turn == 2 && p2.getIsHuman()))
    	{
    		decision = gb.agreeDisagree();
       	}
    	else
    	{
    		Random rand = new Random();
    		int r = rand.nextInt(2);
    		r += 1;	
    		if(r == 1)
    		{
    			decision = true;
    			if(turn == 1)
    				gb.display(p1.getName()+" has agreed.");
    			else
    				gb.display(p2.getName()+" has agreed.");
    			
    		}
    		else
    		{
    			decision = false;
    			if(turn == 1)
    				gb.display(p1.getName()+" has disagreed.");
    			else
    				gb.display(p2.getName()+" has disagreed.");
    		}
    	}
    }
    public static void changeTurn()
    {
    	if(turn == 1)
    		turn = 2;
    	else
    		turn = 1;    	
    }
    public static void checkAnswer()
    {
    	if((decision && qa.getIsRightAns())|| (!decision && !qa.getIsRightAns()))
    		isCorrect = true;
    	else //!(decision && qa.getIsRightAns())
    		isCorrect = false;    	
    }
    public static void checkWinGame()
    {
    	int sc1 = p1.getScore();
    	int sc2 = p2.getScore();
    	if((sc1 >= 500)||(sc2 >= 500))
    	{
    		winGame = true;
    		if(sc1 > sc2)
    			winner = p1.getName();
    		else
    			winner = p2.getName();	
    	}
    }

    public static void checkWinRound()
    {
    	int sc;
    	int mP1 = p1.getNumOfMarks();
    	int mP2 = p2.getNumOfMarks();
    	
    	if( (mP1 +mP2)== 9)
    	{
    		if(mP1 > mP2)
        	{
        		sc = mP1 * 50;
        		p1.updateScore(sc);
        	}
        	else
        	{
        		sc = mP2 * 50;
        		p2.updateScore(sc);
        	}
    		winRound = true;
    	}
    	
       	else
    	{
    		int b1= board[0][0];
        	int b2= board[0][1];
        	int b3= board[0][2];
        	int b4= board[1][0];
        	int b5= board[1][1];
        	int b6= board[1][2];
        	int b7= board[2][0];
        	int b8= board[2][1];
        	int b9= board[2][2];
        	int m = board[row][column]; //holds mark (0 or 1) for the latest marked block
        	        	
        	if( ((m == b1) && ((b1==b2)&&(b2==b3)))||
        		((m == b1) && ((b1==b4)&&(b4==b7)))|| 
        		((m == b1) && ((b1==b5)&&(b5==b9)))||
        		((m == b2) && ((b2==b5)&&(b5==b8)))||
        		((m == b3) && ((b3==b5)&&(b5==b7)))|| 
        		((m == b3) && ((b3==b6)&&(b6==b9)))||
        		((m == b4) && ((b4==b5)&&(b5==b6)))||
        		((m == b7) && ((b7==b8)&&(b8==b9))))
        		{
        			winRound = true;
        			if(m == p1.getMark())
        				p1.updateScore(250);
        			else
        				p2.updateScore(250);
        			
        			gb.updateScore(m, 250);
        		}
    	}
    		
    }
    public static void checkOppWin(Player p)
    {
    	int oppMark = p.getMark();
    	if(p.getNumOfMarks()>=2)
    	{
    		if(block == 1)
    		{
    			if( (oppMark == board[0][1]) && (board[0][1] == board[0][2])||
    				(oppMark == board[1][0]) && (board[1][0] == board[2][0])||
    				(oppMark == board[1][1]) && (board[1][1] == board[2][2]))
    			{
    				isOppWin = true;
    			}
    		}
    		else if(block == 2)
    		{
    			if( (oppMark == board[0][0]) && (board[0][0] == board[0][2])||
        			(oppMark == board[1][1]) && (board[1][1] == board[2][1]))
        		{
        				isOppWin = true;
        		}
    		}
    		else if(block == 3)
    		{
    			if( (oppMark == board[0][1]) && (board[0][1] == board[0][0])||
        			(oppMark == board[1][1]) && (board[1][1] == board[2][0])||
            		(oppMark == board[1][2]) && (board[1][2] == board[2][2]))
        		{
        				isOppWin = true;
        		}
    		}
    		else if(block == 4)
    		{
    			if( (oppMark == board[0][0]) && (board[0][0] == board[2][0])||
        			(oppMark == board[1][1]) && (board[1][1] == board[1][2]))
    			{
        				isOppWin = true;
        		}
    		}
    		else if(block == 5)
    		{
    			if( (oppMark == board[0][0]) && (board[0][0] == board[2][2])||
        			(oppMark == board[1][0]) && (board[1][0] == board[1][2])||
        			(oppMark == board[2][0]) && (board[2][0] == board[0][2])||
            		(oppMark == board[0][1]) && (board[0][1] == board[2][1]))
        		{
        				isOppWin = true;
        		}
    		}
    		else if(block == 6)
    		{
    			if( (oppMark == board[1][1]) && (board[1][1] == board[1][0])||
        			(oppMark == board[0][2]) && (board[0][2] == board[2][2]))
    			{
        				isOppWin = true;
        		}
    		}
    		else if(block == 7)
    		{
    			if( (oppMark == board[1][0]) && (board[1][0] == board[0][0])||
        			(oppMark == board[2][1]) && (board[2][1] == board[2][2])||
            		(oppMark == board[1][1]) && (board[1][1] == board[0][2]))
        		{
        				isOppWin = true;
        		}
    		}
    		else if(block == 8)
    		{
    			if( (oppMark == board[2][0]) && (board[2][0] == board[2][2])||
        			(oppMark == board[1][1]) && (board[1][1] == board[0][1]))
    			{
        				isOppWin = true;
        		}
    		}
    		else if(block == 9)
    		{
    			if( (oppMark == board[1][2]) && (board[1][2] == board[0][2])||
        			(oppMark == board[1][1]) && (board[1][1] == board[0][0])||
            		(oppMark == board[2][1]) && (board[2][1] == board[2][0]))
        		{
        				isOppWin = true;
        		}
    		}
    		else
    			isOppWin = false;
    	}
    	else
    	{
    		isOppWin = false;
    	}
    	
    	gb.display("after checking... Opponent win?: "+isOppWin+"\n");
    	
    }
    public static void markBlock(Player p)
    {
    	int mark = p.getMark();
    	board[row][column]= mark;
    	p.incNumOfMarks();
    	
    	switch(block)
    	{
    	case 1:
    		if(mark == 0)
    			gb.updateBoard(0);
    		else
    			gb.updateBoard(1); 
    		break;
    	case 2:
    		if(mark == 0)
    			gb.updateBoard(10);
    		else
    			gb.updateBoard(11);
    		break;
    	case 3:
    		if(mark == 0)
    			gb.updateBoard(20);
    		else
    			gb.updateBoard(21); 
    		break;
    	case 4:
    		if(mark == 0)
    			gb.updateBoard(100);
    		else
    			gb.updateBoard(101); 
    		break;
    	case 5:
    		if(mark == 0)
    			gb.updateBoard(110);
    		else
    			gb.updateBoard(111);
    		break;
    	case 6:
    		if(mark == 0)
    			gb.updateBoard(120);
    		else
    			gb.updateBoard(121); 
    		break;
    	case 7:
    		if(mark == 0)
    			gb.updateBoard(200);
    		else
    			gb.updateBoard(201); 
    		break;
    	case 8:
    		if(mark == 0)
    			gb.updateBoard(210);
    		else
    			gb.updateBoard(211);
    		break;
    	case 9:
    		if(mark == 0)
    			gb.updateBoard(220);
    		else
    			gb.updateBoard(221); 
    		break;
    	}
    	checkWinRound();
    }
    public static void resetBoard()
    {
    	if(!winGame)
    	{
    		board [0][0] = 3;
        	board [0][1] = 3;
        	board [0][2] = 3;
        	board [1][0] = 3;
        	board [1][1] = 3;
        	board [1][2] = 3;
        	board [2][0] = 3;
        	board [2][1] = 3;
        	board [2][2] = 3;
        	
        	gb.cleanBoard();
        	p1.resetnumOfMarks();
        	p2.resetnumOfMarks();
        	winRound = false;
    	}
    }
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.applet.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class GameBoard
{
    //newly added attributes
    private JPanel jp3 = new JPanel();
    private JPanel jp4 = new JPanel();
    private JPanel jp5 = new JPanel();
    private JLabel pl1 = new JLabel("Player 1 (O)");
    private JLabel pl2 = new JLabel("Player 2 (X)");
    private JLabel ques = new JLabel("Question");
    private JLabel ans = new JLabel("Answer");
    private ImageIcon o = new ImageIcon("o.jpg");
    private ImageIcon x = new ImageIcon("x.jpg");
    private ImageIcon im = new ImageIcon("tomCruise.jpg");
    private ImageIcon im2 = new ImageIcon("mariahCarey.jpg");
    private ImageIcon im3 = new ImageIcon("derekJeter.jpg");
    private ImageIcon im4 = new ImageIcon("bradPitt.jpg");
    private ImageIcon im5 = new ImageIcon("angelinaJolie.jpg");
    private ImageIcon im6 = new ImageIcon("rihanna.jpg");
    private ImageIcon im7 = new ImageIcon("ladyGaGa.jpg");
    private ImageIcon im8 = new ImageIcon("cameronDiaz.jpg");
    private ImageIcon im9 = new ImageIcon("barackObama.jpg");
    //end new
    
    private JFrame jf = new JFrame();
    private JTextField question = new JTextField(20);
    private JTextField answer = new JTextField(20);
    private JTextField p1 = new JTextField(10);
    private JTextField p2 = new JTextField(10);
    private JTextField sc1 = new JTextField(10);
    private JTextField sc2 = new JTextField(10);
    private JTextArea disp = new JTextArea(25,40);
    private JButton one = new JButton(im);
    private JButton two = new JButton(im2);
    private JButton three = new JButton(im3);
    private JButton four = new JButton(im4);
    private JButton five = new JButton(im5);
    private JButton six = new JButton(im6);
    private JButton seven = new JButton(im7);
    private JButton eight = new JButton(im8);
    private JButton nine = new JButton(im9);
    private JPanel jp = new JPanel();
    private JPanel jp2 = new JPanel();
    
    public void setPlayers(String n1, String n2)
    {
        p1.setText(n1);
        p2.setText(n2);
    }
    
    public void updateScore(int turn, int score)
    {
        switch(turn)
        {
        case 0:
            sc1.setText(Integer.toString(score));
            disp.append("Player 1 has won this round and gets "+score+" points.");
            return;
        case 1:
            sc2.setText(Integer.toString(score));
            disp.append("Player 2 has won this round and gets "+score+" points.");
            return;
        default:
            disp.append("Error, such player doesn't exists.\n");            
        }
    }
    
    public void updateBoard(int mark)
    {
    	int block = 9;
		int row = 3, temp, col, mrk;
		
		if(mark < 100)
		{
			row = 0;
			if(mark < 10)
			{
				col = 0;
				mrk = mark;
			}
			else
			{
				col = mark/10;
				mrk = mark % 10;
			}
		}	
		else
		{
			row = mark/100;
			temp = mark % 100;
			col = temp/10;
			mrk= temp%10;
		}
		
		if(row == 0)
		{
			if(col == 0)
				block = 0;
			else if(col == 1)
				block = 1;
			else if(col == 2)
				block = 2;
			else
				disp.append("Error");
		}
		else if(row == 1)
		{
			if(col == 0)
				block = 3;
			else if(col == 1)
				block = 4;
			else if(col == 2)
				block = 5;
			else
				disp.append("Error");
		}
		else
		{
			if(col == 0)
				block = 6;
			else if(col == 1)
				block = 7;
			else if(col == 2)
				block = 8;
			else
				disp.append("Error\n");
		}
		if(mrk == 0)
		{
			if(block == 0)
				one.setIcon(o);
			else if(block == 1)
				two.setIcon(o);
			else if(block == 2)
				three.setIcon(o);
			else if(block == 3)
				four.setIcon(o);
			else if(block == 4)
				five.setIcon(o);
			else if(block == 5)
				six.setIcon(o);
			else if(block == 6)
				seven.setIcon(o);
			else if(block == 7)
				eight.setIcon(o);
			else if(block == 8)
				nine.setIcon(o);
			else 
				disp.append("Error: no such block found.\n");
		}
		else if(mrk == 1)
		{
			if(block == 0)
				one.setIcon(x);
			else if(block == 1)
				two.setIcon(x);
			else if(block == 2)
				three.setIcon(x);
			else if(block == 3)
				four.setIcon(x);
			else if(block == 4)
				five.setIcon(x);
			else if(block == 5)
				six.setIcon(x);
			else if(block == 6)
				seven.setIcon(x);
			else if(block == 7)
				eight.setIcon(x);
			else if(block == 8)
				nine.setIcon(x);
			else 
				disp.append("Error: no such block found.\n");
		}
			
    }
    
    public int askDifficulty()
    {
    	String s = JOptionPane.showInputDialog(null,"Please enter a level of difficulty:\n"+
    			"E for easy, M for medium, and H for hard.");
    	if(s == null)
    	{
    		System.exit(0);
    	}
    	if((s.compareTo("E") == 0) || (s.compareTo("e") == 0))
    	{
    		disp.append("You have selected difficulty: Easy\n");
    		return 1;
    	}
    	else if((s.compareTo("M") == 0) || (s.compareTo("m") == 0))
    	{
    		disp.append("You have selected difficulty: Medium\n");
    		return 2;
    	}
    	else if((s.compareTo("H") == 0) || (s.compareTo("h") == 0))
    	{
    		disp.append("You have selected difficulty: Hard\n");
    		return 3;
    	}
    	else
    	{
    		disp.append("Input "+s+ " is invalid.\n");
    		return 0;
    	}
    }
    
    public int askNumOfPlayers()
    {
        Object[] pV = {"One Player", "Two Players"};
        Object select = JOptionPane.showInputDialog(null,
                "Please select the number of players.", "Number of Players",
                JOptionPane.OK_OPTION, null, pV, null);
        if(select == null)
        	System.exit(0);
        if(select == pV[0])
            return 1;    
        else
            return 2;
    }
    
    public int getBlock()
    {
        Object[] pV = {"Tom Cruise", "Mariah Carey","Derek jeter","Brad Pitt",
        		"Angelina Jolie", "Rihanna","Lady GaGa","Cameron Diaz",
        		"Barack Obama"};
        Object select = JOptionPane.showInputDialog(null,
                "Please select a celebrity.", "Choose a Celebrity",
                JOptionPane.OK_OPTION, null, pV, null);
        if(select == null)
        	System.exit(0);
        if(select == pV[0])
            return 1;
        else if(select == pV[1])
            return 2;
        else if(select == pV[2])
            return 3;
        else if(select == pV[3])
            return 4;
        else if(select == pV[4])
            return 5;
        else if(select == pV[5])
            return 6;
        else if(select == pV[6])
            return 7;
        else if(select == pV[7])
            return 8;
        else
            return 9;
    }
    
    public void displayQuestion(String q)
    {
    	question.setText(q+"\n");
    }
    
    public void displayAnswer(String a)
    {
        answer.setText(a+"\n");
    }
    
    public boolean agreeDisagree()
    {
       Object[] pV = {"Agree", "Disagree"};
        Object select = JOptionPane.showInputDialog(null,
                "Please make your decision.", "Decision",
                JOptionPane.OK_OPTION, null, pV, null);
        if(select == null)
        	System.exit(0);
        if(select == pV[0])
            return true;
        else
        	return false;
    	
    }
    
    //change name
    public void display(String w)
    {
        disp.append(w);
    }
    
    public void cleanBoard()
    {
        one.setIcon(im);
        two.setIcon(im2);
        three.setIcon(im3);
        four.setIcon(im4);
        five.setIcon(im5);
        six.setIcon(im6);
        seven.setIcon(im7);
        eight.setIcon(im8);
        nine.setIcon(im9);
        disp.setText(null);
    }
    
    public GameBoard()
    {
        jf.setResizable(false);
        jf.setTitle("Hollywood Squares");
        jf.setLayout(new BorderLayout());
        jf.setSize(1110,700);
        jp.setLayout(new GridLayout(3,3));
        jf.add(jp, BorderLayout.CENTER);
        jf.add(jp2, BorderLayout.EAST);
        jp2.setBackground(Color.WHITE);
        jp2.setLayout(new BorderLayout());
        jp2.add(jp5, BorderLayout.NORTH);
        jp2.add(disp, BorderLayout.SOUTH);
        jp5.setLayout(new BorderLayout());
        jp5.setBackground(Color.WHITE);
        jp5.add(jp4, BorderLayout.NORTH);
        jp5.add(jp3, BorderLayout.CENTER);
        jp4.setLayout(new GridLayout(4,2));
        jp4.setBackground(Color.WHITE);
        jp3.setLayout(new GridLayout(4,2));
        jp3.setBackground(Color.WHITE);
        jp.add(one);
        jp.add(two);
        jp.add(three);
        jp.add(four);
        jp.add(five);
        jp.add(six);
        jp.add(seven);
        jp.add(eight);
        jp.add(nine);
        jp4.add(pl1);
        jp4.add(pl2);
        jp4.add(p1);
        jp4.add(p2);
        jp4.add(sc1);
        jp4.add(sc2);
        jp3.add(ques);
        jp3.add(question);
        jp3.add(ans);
        jp3.add(answer);
        p1.setEditable(false);
        p2.setEditable(false);
        sc1.setEditable(false);
        sc2.setEditable(false);
        question.setEditable(false);
        answer.setEditable(false);
        disp.append("Welcome to Hollywood Squares, let's play!\n");
        (jf.getContentPane()).setBackground(Color.WHITE);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}//end GameBoard class

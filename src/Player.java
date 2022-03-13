
public class Player 
{
	private String name;
	private boolean isHuman;
	private int mark;
	private int numOfMarks = 0;
	private int score = 0;
	
	Player (String n, int m, boolean isH)
	{
		name = n;
		mark = m;
		isHuman = isH;
	}
	public String getName()
	{
		return name;
	}
	public int getMark()
	{
		return mark;
	}
	public int getScore()
	{
		return score;
	}
	public void updateScore(int sc)
	{
		score = score + sc;
	}
	public void incNumOfMarks()
	{
		numOfMarks ++;
	}
	public int getNumOfMarks()
	{
		return numOfMarks;
	}
	public boolean getIsHuman()
	{
		return isHuman;
	}
	public void resetnumOfMarks()
	{
		numOfMarks = 0;
	}

}

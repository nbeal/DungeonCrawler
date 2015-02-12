import java.util.Random;

public class Enemy extends DungeonCharacter
{
	
	private Enemy()
	{
		super();
	}
	
	private Enemy(int hp, int str, int dex, int stam, int def, String tempName)
	{
		super(hp, str, dex, stam, def, tempName);
		
	}
	
	public boolean isHero()
	{
		return false;
	}
	
	public int hasItem() //will reference a XML doc of Items
	{
		Random rand = new Random();
		int max = 10;
	    int randomNum = rand.nextInt((max - 1)) + 1;
	    
	    if(randomNum < 2) //20% chance of dropping
	    {
	    	int index = rand.nextInt((max - 1)) + 1; //need to tailor to size of XML doc
	    }
	    else
	    	return -1; //int of no item
		return randomNum;
	}

}

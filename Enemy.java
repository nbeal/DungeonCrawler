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
	
	public void isHero()
	{
		return false;
	}
	
	public int hasItem() //will reference a XML doc of Items
	{
		Random rand = new Random();
	    int randomNum = rand.nextInt((10 - 1) + 1) + min;
	    
	    if(randomNum < 2) //20% chance of dropping
	    {
	    	int index = rand.nextInt((10 - 1) + 1) + min; //need to tailor to size of XML doc
	    }
	    else
	    	return -1; //int of no item
	}

}
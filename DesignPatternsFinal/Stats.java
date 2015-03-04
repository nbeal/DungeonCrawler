package DesignPatternsFinal;
// HP, Strength, Dexterity, Stamina, Defense

public class Stats
{
	private int[] stats;
	public Stats(String name) 
	{
		if(name.equals("Blob"))
		{
			stats = new int[] {12, 3, 4, 1, 1};
		}
		else
		if(name.equals("Wizard"))
		{
			stats = new int[] {30, 3, 5, 30, 1};
		}
		else
		if(name.equals("Paladin"))
		{
			stats = new int[] {50, 10, 5, 15, 10};
		}
		else
		if(name.equals("Barbarian"))
		{
			stats = new int[] {100, 12, 5, 25, 0};
		}
		else
		if(name.equals("Cleric"))
		{
			stats = new int[] {40, 6, 5, 30, 5};
		}
		if(name.equals("Ranger"))
		{
			stats = new int[] {40, 5, 8, 20, 5};
		}

	}
	public int[] getStats() 
	{
		return this.stats;
	}
	
	public void levelUp()
	{
		stats[0] += 5;
		stats[1] += 5;
		stats[2] += 5;
		stats[3] += 5;
		stats[4] += 5;
	}
	
}
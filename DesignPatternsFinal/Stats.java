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

	}
	public int[] getStats() 
	{
		return this.stats;
	}
	
}
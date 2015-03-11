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
        if(name.equals("Goblin"))
        {
            stats = new int[] {12, 3, 6, 1, 3};
        }
        else
        if(name.equals("Skeleton"))
        {
            stats = new int[] {15, 6, 4, 20, 3};
        }
        else
        if(name.equals("Giant Spider"))
        {
            stats = new int[] {25, 8, 4, 30, 4};
        }
        else
        if(name.equals("Zombie"))
        {
            stats = new int[] {10, 8, 2, 10, 5};
        }
        else
        if(name.equals("Cave Troll"))
        {
            stats = new int[] {50, 12, 4, 25, 10};
        }
        else
        if(name.equals("Orc"))
        {
            stats = new int[] {30, 8, 4, 25, 8};
        }
        else
        if(name.equals("Dread Lord"))
        {
            stats = new int[] {200, 10, 4, 50, 10};
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
	
	public void levelUp(int hp, int str, int dex, int stam, int def)
	{
		stats[0] += hp;
		stats[1] += str;
		stats[2] += dex;
		stats[3] += stam;
		stats[4] += def;
	}
	
}
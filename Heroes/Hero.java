package Heroes;

import DesignPatternsFinal.DungeonCharacter;
import DesignPatternsFinal.Stats;
import Equipment.Equipment;

public class Hero extends DungeonCharacter
{

	public Hero()
	{
		super();
	}
	
	public Hero(String enName, Stats stats)
	{
			super(enName, stats);
	}

	
	public Hero(int hp, int str, int dex, int stam, int def, String tempName)
	{
		super(hp, str, dex, stam, def, tempName);
		
	}
	
	public boolean isHero()
	{
		return true;
	}
}
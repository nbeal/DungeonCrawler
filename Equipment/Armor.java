package Equipment;

import DesignPatternsFinal.DamageHandler;

public class Armor extends Equipment
{
	public Armor (String[] stats)
	{
		super();
		//public Equipment(int hp, int str, int dex, int stam, String def, String tempName)
		int health = Integer.parseInt(stats[1]);
		int strength = Integer.parseInt(stats[2]);
		int dextarity = Integer.parseInt(stats[3]);
		int stamina = Integer.parseInt(stats[4]);
		String defense = ParseDefense(stats[5]);
		String name = stats[6];
		RedoEquipment(health, strength, dextarity, stamina, defense, name);
		super.type = stats[7];
	}
	
	private String ParseDefense(String string)
	{
		String[] splitAttack = string.split(",");
		String properAttack = "";
		for (int i = 0; i < splitAttack.length; i++)
		{
			properAttack = properAttack + DamageHandler.getValue(splitAttack[i]);
			if (i + 1 < splitAttack.length)
				properAttack = properAttack + ",";
		}
		return properAttack;
	}
}
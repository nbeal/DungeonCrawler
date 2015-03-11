package Equipment;

import Attack.AttackType;
import DesignPatternsFinal.DamageHandler;
import Items.Item;

public class Weapon extends Equipment
{

	public Weapon (String[] stats)
	{
		super();
		//public Equipment(int hp, int str, String atk , int dex, int stam, String tempName)
		int health = Integer.parseInt(stats[1]);
		int strength = Integer.parseInt(stats[2]);
		String attack = ParseAttack(stats[3]);
		int dextarity = Integer.parseInt(stats[4]);
		double stamina = Double.parseDouble(stats[5]);
		String name = stats[6];
		RedoEquipment(health, strength, attack, dextarity, stamina, name);
		super.type = "weapon";
		attackResolve(stats[7]);
	}

	private void attackResolve(String attack)
	{
		try
		{
			String attackName = "Attack." + attack;
        	Class c = Class.forName(attackName);

        	Object o = c.newInstance();

        	this.Attack = (AttackType) o;
		}
		catch(Exception e)
		{
			 e.printStackTrace();
		}
		
	}

	private String ParseAttack(String string)
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

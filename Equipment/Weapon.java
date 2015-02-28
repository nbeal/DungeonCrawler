package Equipment;

public class Weapon extends Equipment
{
	public Weapon (String[] stats)
	{
		super();
		//public Equipment(int hp, int str, String atk , int dex, int stam, String tempName)
		int health = Integer.parseInt(stats[1]);
		int strength = Integer.parseInt(stats[2]);
		String attack = stats[3];
		int dextarity = Integer.parseInt(stats[4]);
		int stamina = Integer.parseInt(stats[5]);
		String name = stats[6];
		RedoEquipment(health, strength, attack, dextarity, stamina, name);
		super.type = "weapon";
	}
}

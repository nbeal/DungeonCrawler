public abstract class SpecialAttack
{
	int stamUsed;
	String name;
	int damage;
	public int attack(DungeonCharacter defender)
	{
		return defender.modifyHealth(getDamage());
	
	}
	
	public String getAttackName()
	{
		return name;
	}
	public int getDamage()
	{
		return damage;
	}
	
	public int getStamUsed()
	{
		return damage;
	}

}


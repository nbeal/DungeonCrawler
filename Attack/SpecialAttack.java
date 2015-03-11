package Attack;

public abstract class SpecialAttack
{
	int stamUsed;
	String name;
	String action;
	int damageType;
	int damage;
	public String attack()
	{
		//return defender.modifyHealth(getDamage());
		return getDamage();
	}
	
	public String getAttackName()
	{
		return name;
	}
	private String getDamage()
	{
		return damageType + "," + damage;
	}
	
	public int getStamUsed()
	{
		return damage;
	}

	public String getAction()
	{
		return action;
	}

}


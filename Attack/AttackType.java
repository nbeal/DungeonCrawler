package Attack;

public abstract class AttackType
{
	String name;
	int damageType;
	int damage;
	public String attack()
	{
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
}

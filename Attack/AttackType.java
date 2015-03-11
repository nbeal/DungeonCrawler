package Attack;

public abstract class AttackType
{
	String name;
	String action;
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
	
	public String getAction()
	{
		return action;
	}
	private String getDamage()
	{
		return damageType + "," + damage;
	}
}

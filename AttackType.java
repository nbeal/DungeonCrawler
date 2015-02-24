
public abstract class AttackType
{
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
	}}

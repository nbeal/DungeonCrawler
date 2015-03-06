package Attack;

import DesignPatternsFinal.DamageHandler;

public class Bite extends AttackType
{
	public Bite()
	{
		name = "Bite";
		damage = 6;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
}

package Attack;

import DesignPatternsFinal.DamageHandler;

public class Bite extends AttackType
{
	public Bite()
	{
		name = "Bite";
		action = "bites";
		damage = 6;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
}

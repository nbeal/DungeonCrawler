package Attack;


import DesignPatternsFinal.DamageHandler;

public class Stab extends AttackType
{
	public Stab()
	{
		name = "Stab";
		action = "stabs";
		damage = 5;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
}

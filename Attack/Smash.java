package Attack;

import DesignPatternsFinal.DamageHandler;

public class Smash extends AttackType
{
	public Smash()
	{
		name = "Smash";
		action = "smashes";
		damage = 8;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
}
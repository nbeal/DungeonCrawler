package Attack;

import DesignPatternsFinal.DamageHandler;

public class Slash extends AttackType
{
	public Slash()
	{
		name = "Slash";
		action = "slashes";
		damage = 6;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
}
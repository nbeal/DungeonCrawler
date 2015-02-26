package Attack;

import DesignPatternsFinal.DamageHandler;

public class NullAttack extends AttackType
{
	public NullAttack()
	{
		name = "Nothing";
		damage = 0;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
}
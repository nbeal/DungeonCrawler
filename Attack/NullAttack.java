package Attack;

import DesignPatternsFinal.DamageHandler;

public class NullAttack extends AttackType
{
	public NullAttack()
	{
		name = "Nothing";
		action = "pondered the reason for attacking with";
		damage = 0;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
}
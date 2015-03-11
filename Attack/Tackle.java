package Attack;

import DesignPatternsFinal.DamageHandler;

public class Tackle extends AttackType
{
	
	public Tackle()
	{
		name = "Tackle";
		action = "tackles";
		damage = 4;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
}
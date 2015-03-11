package Attack;

import DesignPatternsFinal.DamageHandler;

public class Shoot extends AttackType
{
	public Shoot()
	{
		name = "Shoot";
		action = "looses an arrow at";
		damage = 6;
		damageType = DamageHandler.DAMAGE_PIERCE;
	}
}
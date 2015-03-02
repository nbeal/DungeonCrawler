package Attack;

import DesignPatternsFinal.DamageHandler;

public class Shoot extends AttackType
{
	public Shoot()
	{
		name = "Shoot";
		damage = 6;
		damageType = DamageHandler.DAMAGE_PIERCE;
	}
}
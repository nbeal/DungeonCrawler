package Attack;

import DesignPatternsFinal.DamageHandler;

public class Rage extends SpecialAttack
{
	public Rage()
	{
		name = "Rage";
		damage = 18;
		stamUsed = 25;
		damageType = DamageHandler.DAMAGE_PIERCE;
	}
}
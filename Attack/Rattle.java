package Attack;

import DesignPatternsFinal.DamageHandler;

public class Rattle extends SpecialAttack
{
	public Rattle()
	{
		name = "Rattle";
		action = "rattles";
		damage = 10;
		stamUsed = 10;
		damageType = DamageHandler.DAMAGE_PIERCE;
	}
}
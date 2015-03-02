package Attack;

import DesignPatternsFinal.DamageHandler;

public class RapidShot extends SpecialAttack
{
	public RapidShot()
	{
		stamUsed = 10;
		name = "Rapid Shot";
		damage = 12; 
		damageType = DamageHandler.DAMAGE_PIERCE;
	}

}
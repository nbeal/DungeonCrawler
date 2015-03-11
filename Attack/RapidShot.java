package Attack;

import DesignPatternsFinal.DamageHandler;

public class RapidShot extends SpecialAttack
{
	public RapidShot()
	{
		stamUsed = 10;
		name = "Rapid Shot";
		action = "rapidly fires a flurry of arrows at";
		damage = 12; 
		damageType = DamageHandler.DAMAGE_PIERCE;
	}

}
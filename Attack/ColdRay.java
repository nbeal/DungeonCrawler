package Attack;

import DesignPatternsFinal.DamageHandler;

public class ColdRay extends SpecialAttack
{
	public ColdRay()
	{
		stamUsed = 10;
		name = "Ray of Cold";
		action = "fires a freezing ray at";
		damage = 15; 
		damageType = DamageHandler.DAMAGE_COLD;
	}

}

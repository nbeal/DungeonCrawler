package Attack;

import DesignPatternsFinal.DamageHandler;

public class ColdRay extends SpecialAttack
{
	public ColdRay()
	{
		stamUsed = 10;
		name = "Ray of Cold";
		damage = 10; 
		damageType = DamageHandler.DAMAGE_COLD;
	}

}

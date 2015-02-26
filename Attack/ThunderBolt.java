package Attack;

import DesignPatternsFinal.DamageHandler;

public class ThunderBolt extends SpecialAttack
{
	public ThunderBolt()
	{
		stamUsed = 10;
		name = "Thunder Bolt";
		damage = 10; 
		damageType = DamageHandler.DAMAGE_ELEC;
	}

}
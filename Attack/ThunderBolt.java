package Attack;

import DesignPatternsFinal.DamageHandler;

public class ThunderBolt extends SpecialAttack
{
	public ThunderBolt()
	{
		stamUsed = 10;
		name = "Thunder Bolt";
		action = "shoots a bolt of lightning at";
		damage = 10; 
		damageType = DamageHandler.DAMAGE_ELEC;
	}

}
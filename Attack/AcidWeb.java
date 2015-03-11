package Attack;

import DesignPatternsFinal.DamageHandler;

public class AcidWeb extends SpecialAttack
{
	public AcidWeb()
	{
		name = "Acid Web";
		action = "shoots a web made of acid";
		damage = 15;
		stamUsed = 15;
		damageType = DamageHandler.DAMAGE_ACID;
	}
}
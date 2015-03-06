package Attack;

import DesignPatternsFinal.DamageHandler;

public class AcidWeb extends SpecialAttack
{
	public AcidWeb()
	{
		name = "Acid Web";
		damage = 15;
		stamUsed = 15;
		damageType = DamageHandler.DAMAGE_ACID;
	}
}
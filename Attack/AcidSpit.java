package Attack;

import DesignPatternsFinal.DamageHandler;

public class AcidSpit extends SpecialAttack
{
	public AcidSpit()
	{
		name = "Acid Spit";
		action = "spits acid";
		damage = 10;
		stamUsed = 10;
		damageType = DamageHandler.DAMAGE_ACID;
	}
}
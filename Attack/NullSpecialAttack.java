package Attack;

import DesignPatternsFinal.DamageHandler;

public class NullSpecialAttack extends SpecialAttack
{

	public NullSpecialAttack()
	{
		stamUsed = 0;
		name = "NA";
		action = "kinda just looked at";
		damage = 0;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
	
}
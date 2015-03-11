package Attack;

import DesignPatternsFinal.DamageHandler;

public class Heal extends SpecialAttack
{
	public Heal()
	{
		name = "Heal";
		action = "heals";
		damage = -10;
		stamUsed = 10;
		damageType = DamageHandler.DAMAGE_HEAL;
	}
}
package Attack;

import DesignPatternsFinal.DamageHandler;

public class Rage extends SpecialAttack
{
	public Rage()
	{
		name = "Rage";
		action = "lets it's rage loose on";
		damage = 18;
		stamUsed = 25;
		damageType = DamageHandler.DAMAGE_PIERCE;
	}
}
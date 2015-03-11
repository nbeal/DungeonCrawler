package Attack;

import DesignPatternsFinal.DamageHandler;

public class HolyStrike extends SpecialAttack
{
	public HolyStrike()
	{
		name = "Holy Strike";
		action = "charges up a powerful attack and strikes";
		damage = 12;
		stamUsed = 6;
		damageType = DamageHandler.DAMAGE_PIERCE;
	}
}
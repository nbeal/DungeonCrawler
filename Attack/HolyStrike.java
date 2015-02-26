package Attack;

import DesignPatternsFinal.DamageHandler;

public class HolyStrike extends SpecialAttack
{
	public HolyStrike()
	{
		name = "Holy Strike";
		damage = 12;
		stamUsed = 6;
		damageType = DamageHandler.DAMAGE_PIERCE;
	}
}
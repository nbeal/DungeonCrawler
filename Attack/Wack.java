package Attack;


import DesignPatternsFinal.DamageHandler;

public class Wack extends AttackType
{
	public Wack()
	{
		name = "Wack";
		action = "hits";
		damage = 3;
		damageType = DamageHandler.DAMAGE_NORMAL;
	}
}


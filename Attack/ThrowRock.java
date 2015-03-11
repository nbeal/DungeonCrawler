package Attack;

import DesignPatternsFinal.DamageHandler;

public class ThrowRock extends SpecialAttack
{
    public ThrowRock()
    {
        name = "Throw Rock";
        damage = 20;
        stamUsed = 25;
        damageType = DamageHandler.DAMAGE_NORMAL;
    }
}
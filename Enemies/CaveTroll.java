package Enemies;

import Attack.Smash;
import Attack.ThrowRock;
import DesignPatternsFinal.Stats;

public class CaveTroll extends Enemy
{
    public CaveTroll()
    {
        super();
    }

    public CaveTroll(Stats stats)
    {
        super("Cave Troll", stats);

        attacktype = new Smash();
        special = new ThrowRock();
        this.setExp(25);
    }

}

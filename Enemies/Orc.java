package Enemies;

import Attack.Rage;
import Attack.Slash;
import DesignPatternsFinal.Stats;

public class Orc extends Enemy
{
    public Orc()
    {
        super();
    }

    public Orc(Stats stats)
    {
        super("Orc", stats);

        attacktype = new Slash();
        special = new Rage();
        this.setExp(20);
    }

}
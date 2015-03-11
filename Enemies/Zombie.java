package Enemies;

import Attack.AcidSpit;
import Attack.Bite;
import DesignPatternsFinal.Stats;

public class Zombie extends Enemy
{
    public Zombie()
    {
        super();
    }

    public Zombie(Stats stats)
    {
        super("Zombie", stats);

        attacktype = new Bite();
        special = new AcidSpit();
        this.setExp(10);
    }

}

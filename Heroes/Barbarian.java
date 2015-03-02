package Heroes;

import Attack.Rage;
import Attack.Smash;
import DesignPatternsFinal.Stats;

public class Barbarian extends Hero
{
	public Barbarian(Stats stats)
	{
		super("Barbarian", stats);
		attacktype = new Smash();
		special = new Rage();
	}
}
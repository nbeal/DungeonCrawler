package Heroes;

import Attack.Heal;
import Attack.Smash;
import DesignPatternsFinal.Stats;

public class Cleric extends Hero
{
	public Cleric(Stats stats)
	{
		super("Cleric", stats);
		attacktype = new Smash();
		special = new Heal();
	}
}
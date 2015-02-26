package Heroes;

import Attack.HolyStrike;
import Attack.Slash;
import DesignPatternsFinal.Stats;

public class Paladin extends Hero
{
	public Paladin(Stats stats)
	{
		super("Paladin", stats);
		attacktype = new Slash();
		special = new HolyStrike();
	}
}
package Heroes;

import Attack.Tackle;
import Attack.ThunderBolt;
import DesignPatternsFinal.Stats;

public class Wizard extends Hero
{
	public Wizard(Stats stats)
	{
		super("Wizard", stats);
		attacktype = new Tackle();
		special = new ThunderBolt();
	}
}
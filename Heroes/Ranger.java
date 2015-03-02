package Heroes;

import Attack.RapidShot;
import Attack.Shoot;
import DesignPatternsFinal.Stats;

public class Ranger extends Hero
{
	public Ranger(Stats stats)
	{
		super("Ranger", stats);
		attacktype = new Shoot();
		special = new RapidShot();
	}
}
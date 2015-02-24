public class Wizard extends Hero
{
	public Wizard(Stats stats)
	{
		super("Wizard", stats);
		attacktype = new Tackle();
		special = new ThunderBolt();
	}
}
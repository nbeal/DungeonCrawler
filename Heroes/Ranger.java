package Heroes;

import Attack.RapidShot;
import Attack.Shoot;
import DesignPatternsFinal.Stats;
import Equipment.Equipment;
import Equipment.EquipmentFactory;

public class Ranger extends Hero
{
	public Ranger(Stats stats)
	{
		super("Ranger", stats);
		Equipment item = EquipmentFactory.getInstance().loadEquipment("Ranger", 1);
		this.equip(item);
		special = new RapidShot();
	}
}
package Heroes;

import Attack.Tackle;
import Attack.ThunderBolt;
import DesignPatternsFinal.Stats;
import Equipment.Equipment;
import Equipment.EquipmentFactory;

public class Wizard extends Hero
{
	public Wizard(Stats stats)
	{
		super("Wizard", stats);
		Equipment item = EquipmentFactory.getInstance().loadEquipment("Wizard", 1);
		this.equip(item);
		special = new ThunderBolt();
	}
}
package Heroes;

import Attack.Heal;
import Attack.Smash;
import DesignPatternsFinal.Stats;
import Equipment.Equipment;
import Equipment.EquipmentFactory;

public class Cleric extends Hero
{
	public Cleric(Stats stats)
	{
		super("Cleric", stats);
		Equipment item = EquipmentFactory.getInstance().loadEquipment("Cleric", 1);
		this.equip(item);
		special = new Heal();
	}
}
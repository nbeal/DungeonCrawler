package Heroes;

import Attack.Rage;
import Attack.Smash;
import DesignPatternsFinal.Stats;
import Equipment.Equipment;
import Equipment.EquipmentFactory;

public class Barbarian extends Hero
{
	public Barbarian(Stats stats)
	{
		super("Barbarian", stats);
		Equipment item = EquipmentFactory.getInstance().loadEquipment("Barbarian", 1);
		this.equip(item);
		special = new Rage();
	}
}
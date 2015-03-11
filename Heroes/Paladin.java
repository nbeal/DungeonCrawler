package Heroes;

import Attack.HolyStrike;
import Attack.Slash;
import DesignPatternsFinal.Stats;
import Equipment.Equipment;
import Equipment.EquipmentFactory;

public class Paladin extends Hero
{
	public Paladin(Stats stats)
	{
		super("Paladin", stats);
		Equipment item = EquipmentFactory.getInstance().loadEquipment("Paladin", 1);
		this.equip(item);
		special = new HolyStrike();
	}
}
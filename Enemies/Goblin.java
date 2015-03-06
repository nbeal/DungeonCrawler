package Enemies;

import Attack.AcidSpit;
import Attack.AttackType;
import Attack.Slash;
import Attack.SpecialAttack;
import DesignPatternsFinal.Stats;

public class Goblin extends Enemy
{
	public Goblin()
	{
		super();
	}
	
	public Goblin(Stats stats)
	{
		super("Goblin", stats);

		attacktype = new Slash();
		special = new AcidSpit();
		this.setExp(10);
	}
	
}

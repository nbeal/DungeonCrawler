package Enemies;

import Attack.AcidWeb;
import Attack.AttackType;
import Attack.Bite;
import Attack.SpecialAttack;
import DesignPatternsFinal.Stats;

public class GiantSpider extends Enemy
{
	public GiantSpider()
	{
		super();
	}
	
	public GiantSpider(Stats stats)
	{
		super("Giant Spider", stats);

		attacktype = new Bite();
		special = new AcidWeb();
		this.setExp(10);
	}
	
}
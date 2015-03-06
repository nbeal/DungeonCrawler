package Enemies;

import Attack.RapidShot;
import Attack.Shoot;
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
		attacktype = new Shoot();
		special = new RapidShot();
		this.setExp(25);
	}
}

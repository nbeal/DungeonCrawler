package Enemies;

import Attack.ColdRay;
import Attack.Slash;
import Attack.Rage;
import DesignPatternsFinal.Stats;

public class FinalBoss extends Enemy
{
	public FinalBoss()
	{
		super();
	}
	
	public FinalBoss(Stats stats)
	{
		super("Dread King", stats);

		attacktype = new Slash();
		special = new ColdRay();
		special = new Rage();
		this.setExp(500); //don't think this matters since you win, haha
	}
	
}

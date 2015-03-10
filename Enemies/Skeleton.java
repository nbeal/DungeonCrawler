package Enemies;

import Attack.Rattle;
import Attack.AttackType;
import Attack.Shoot;
import Attack.Slash;
import Attack.SpecialAttack;
import DesignPatternsFinal.Stats;

public class Skeleton extends Enemy
{
	public Skeleton()
	{
		super();
	}
	
	public Skeleton(Stats stats)
	{
		super("Skeleton", stats);

		attacktype = new Shoot();
		special = new Rattle();
		this.setExp(10);
	}
	
}
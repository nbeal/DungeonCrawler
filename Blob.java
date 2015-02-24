public class Blob extends Enemy
{
	public Blob()
	{
		super();
	}
	
	public Blob(Stats stats)
	{
		super("Blob", stats);
		/*int[] stats = statFact.getStats(enemyName).getStats();
		setStats(stats[0], stats[1], stats[2], stats[3], stats[4], enemyName);
		*/
		attacktype = new Tackle();
		special = new NullSpecialAttack();
	}
	
}
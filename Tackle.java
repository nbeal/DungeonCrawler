public class Tackle implements AttackType
{
	
	public void attack(DungeonCharacter defender)
	{
		defender.modifyHealth(4);
	}

}
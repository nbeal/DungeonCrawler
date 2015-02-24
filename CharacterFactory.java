public class CharacterFactory
{
	public DungeonCharacter orderEnemy(String name)
	{
		StatsFactory statFact = StatsFactory.getStatFact();
		
		if(name.equals("Blob"))
			return new Blob(statFact.getStats("Blob"));
		else
		if(name.equals("Wizard"))
			return new Wizard(statFact.getStats("Wizard"));
		else
		if(name.equals("Paladin"))
			return new Paladin(statFact.getStats("Paladin"));
		
			
		return null;
	}

}
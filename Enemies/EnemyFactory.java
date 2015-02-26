package Enemies;

import DesignPatternsFinal.CharacterFactory;
import DesignPatternsFinal.DungeonCharacter;
import DesignPatternsFinal.StatsFactory;


public class EnemyFactory implements CharacterFactory
{
	public DungeonCharacter order(String name)
	{
		StatsFactory statFact = StatsFactory.getStatFact();
		
		if(name.equals("Blob"))
			return new Blob(statFact.getStats("Blob"));

		return null;
	}
}

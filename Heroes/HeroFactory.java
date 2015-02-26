package Heroes;

import DesignPatternsFinal.CharacterFactory;
import DesignPatternsFinal.DungeonCharacter;
import DesignPatternsFinal.StatsFactory;

public class HeroFactory implements CharacterFactory
{
	public DungeonCharacter order(String name)
	{
		StatsFactory statFact = StatsFactory.getStatFact();
		
		if(name.equals("Wizard"))
			return new Wizard(statFact.getStats("Wizard"));
		else
		if(name.equals("Paladin"))
			return new Paladin(statFact.getStats("Paladin"));
		
		return null;
	}
}

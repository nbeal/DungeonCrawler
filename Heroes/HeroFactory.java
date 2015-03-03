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
		else
		if(name.equals("Cleric"))
			return new Cleric(statFact.getStats("Cleric"));
		else
		if(name.equals("Ranger"))
			return new Ranger(statFact.getStats("Ranger"));
		else
		if(name.equals("Barbarian"))
			return new Barbarian(statFact.getStats("Barbarian"));
		
		return null;
	}
	
	public String[] getTypes()
	{
		return new String[] {"Wizard", "Paladin", "Cleric", "Ranger", "Barbarian"};
	}
}

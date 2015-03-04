package Heroes;

import DesignPatternsFinal.CharacterFactory;
import DesignPatternsFinal.DungeonCharacter;
import DesignPatternsFinal.Stats;


public class HeroFactory implements CharacterFactory
{
	public DungeonCharacter order(String name)
	{
		
		if(name.equals("Wizard"))
			return new Wizard(new Stats("Wizard"));
		else
		if(name.equals("Paladin"))
			return new Paladin(new Stats("Paladin"));
		else
		if(name.equals("Cleric"))
			return new Cleric(new Stats("Cleric"));
		else
		if(name.equals("Ranger"))
			return new Ranger(new Stats("Ranger"));
		else
		if(name.equals("Barbarian"))
			return new Barbarian(new Stats("Barbarian"));
		
		return null;
	}
	

	public String[] getTypes() 
	{
		return new String[] {"Wizard", "Paladin", "Cleric", "Ranger", "Barbarian"};
	}
}

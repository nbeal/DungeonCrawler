package Heroes;

import DesignPatternsFinal.CharacterFactory;
import DesignPatternsFinal.DungeonCharacter;
import DesignPatternsFinal.Stats;


public class HeroFactory implements CharacterFactory
{
	public DungeonCharacter order(String name)
	{
		DungeonCharacter character = null;
		NameFactory nameFact = NameFactory.getNameFact();
		
		if(name.equals("Wizard"))
			character = new Wizard(new Stats("Wizard"));
		else
		if(name.equals("Paladin"))
			character = new Paladin(new Stats("Paladin"));
		else
		if(name.equals("Cleric"))
			character = new Cleric(new Stats("Cleric"));
		else
		if(name.equals("Ranger"))
			character = new Ranger(new Stats("Ranger"));
		else
		if(name.equals("Barbarian"))
			character = new Barbarian(new Stats("Barbarian"));
		
		character.setName(nameFact.getName());
		
		return character;
	}
	

	public String[] getTypes() 
	{
		return new String[] {"Wizard", "Paladin", "Cleric", "Ranger", "Barbarian"};
	}
}

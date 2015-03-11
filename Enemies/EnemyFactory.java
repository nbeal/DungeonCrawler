package Enemies;

import DesignPatternsFinal.CharacterFactory;
import DesignPatternsFinal.DungeonCharacter;
import DesignPatternsFinal.Stats;
import DesignPatternsFinal.StatsFactory;


public class EnemyFactory implements CharacterFactory
{
	public DungeonCharacter order(String name)
	{
		StatsFactory statFact = StatsFactory.getStatFact();
		
		if(name.equals("Blob"))
			return new Blob(statFact.getStats("Blob"));
		else
		if(name.equals("Goblin"))
			return new Goblin(new Stats("Goblin"));
		else
		if(name.equals("Giant Spider"))
			return new GiantSpider(new Stats("GiantSpider"));
        else
        if(name.equals("Skeleton"))
            return new Skeleton(new Stats("Skeleton"));
        else
        if(name.equals("Orc"))
            return new Orc(new Stats("Orc"));
        else
        if(name.equals("Zombie"))
            return new Zombie(new Stats("Zombie"));
        else
        if(name.equals("Cave Troll"))
            return new CaveTroll(new Stats("CaveTroll"));
        else
        if(name.equals("Dread Lord"))
            return new FinalBoss(new Stats("Dread Lord"));

		return null;
	}
}

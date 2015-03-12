package Enemies;

import java.util.Random;

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
			return new Goblin(statFact.getStats("Goblin"));
		else
		if(name.equals("Giant Spider"))
			return new GiantSpider(statFact.getStats("GiantSpider"));
        else
        if(name.equals("Skeleton"))
            return new Skeleton(statFact.getStats("Skeleton"));
        else
        if(name.equals("Orc"))
            return new Orc(statFact.getStats("Orc"));
        else
        if(name.equals("Zombie"))
            return new Zombie(statFact.getStats("Zombie"));
        else
        if(name.equals("Cave Troll"))
            return new CaveTroll(statFact.getStats("Cave Troll"));
        else
        if(name.equals("Dread Lord"))
            return new FinalBoss(statFact.getStats("Dread Lord"));

		return null;
	}
	
	
	
	public DungeonCharacter[] getRandomEnemies(int addedAmount)
	{
		StatsFactory statFact = StatsFactory.getStatFact();
		Random rnd = new Random();
		int enemyNum = rnd.nextInt(4 + addedAmount) + 1;
		DungeonCharacter[] enemies = new DungeonCharacter[enemyNum];
		
		
		for(int k = 0; k < enemyNum; k++)
		{
			double enemyGroup = rnd.nextDouble();
			
			if(enemyGroup > .3)
			{
				int enemyType = rnd.nextInt(5);
				
				if(enemyType == 0)
					enemies[k] = new Blob(statFact.getStats("Blob"));
				else
				if(enemyType == 1)
					enemies[k] = new Goblin(statFact.getStats("Goblin"));
				else
				if(enemyType == 2)
					enemies[k] = new GiantSpider(statFact.getStats("Giant Spider"));
				else
				if(enemyType == 3)
					enemies[k] = new Skeleton(statFact.getStats("Skeleton"));
				else
				if(enemyType == 4)
					enemies[k] = new Zombie(statFact.getStats("Zombie"));
			}
			else
			{

				int enemyType = rnd.nextInt(2);
				
				if(enemyType == 0)
					enemies[k] = new CaveTroll(statFact.getStats("Cave Troll"));
				else
				if(enemyType == 1)
					enemies[k] = new Orc(statFact.getStats("Orc"));
			}
		}
		
		return enemies;
	}
}

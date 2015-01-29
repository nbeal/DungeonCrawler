import java.util.Scanner;

public class Battle
{
	public static void main(String[] args)
	{
		DungeonCharacter monster1 = new Blob();
		DungeonCharacter monster2 = new Blob();
		
		monster1.attack(monster2);
		System.out.println(monster2.getHealth());
	}
	
	public static void battle(DungeonCharacter[] heroes, DungeonCharacter[] enemies)
	{
		DungeonCharacter[] order = concat(heroes, enemies);
		boolean heroStatus = checkStatus(heroes);
		boolean enemyStatus = checkStatus(enemies);
		while(heroStatus && enemyStatus)
		{
			for (int x = 0; x < order.length; x++)
			{
				if (order[x].isAlive())
				{
					//Select Action
					int action = selectAction(order[x]);
					//Select Target (print dead if dead)
						//check alive Status of target
						//if dead select again
					//Damage Target
				}
				//check status for each group;
				//if one is false break
			}
		}
		if (heroStatus)
		{
			System.out.println("You won the battle");
		}
		else
		{
			System.out.println("You lost");
		}
	}

	
	
	
	private static int selectAction(DungeonCharacter attacker)
	{
		//if (hero object isHero())
			System.out.println("What will " + attacker.getName() + " do?");
			System.out.println("1) Attack \n2) Special Attack \n3) Nothing\n");
			Scanner kb = new Scanner(System.in);
			int choice = kb.nextInt();
			while (choice > 3 || choice < 1)
			{
				System.out.println("Invalid, pick again");
				choice = kb.nextInt();
			}
		// TODO Auto-generated method stub
		return choice;
	}

	private static boolean checkStatus(DungeonCharacter[] dudes)
	{
		boolean temp = false;
		for (int x = 0; x < dudes.length; x++ )
		{
			temp = (dudes.isAlive() || temp);
		}
		return temp;
	}

	private static DungeonCharacter[] concat(DungeonCharacter[] heroes, DungeonCharacter[] enemies)
	{
		// TODO Auto-generated method stub
		DungeonCharacter[] temp = new DungeonCharacter[heroes.length + enemies.length];
		int x;
		for (x = 0; x < heroes.length; x++)
		{
			temp[x] = heroes[x];
		}
		for (int i = 0; i < enemies.length; i++)
		{
			temp[x] = enemies[i];
			x++;
		}
		
		//TODO sort temp by dextarity
		
		return temp;
	}

}
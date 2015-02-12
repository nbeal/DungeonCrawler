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
				if (heroStatus && enemyStatus)
				{
					if (order[x].isAlive())
					{
						//Select Action
						int action = selectAction(order[x]);
						//Select Target (print dead if dead)
						int target = selectTarget(order, x);
						//Damage Target
						order[x].attack(order[target]);
						//check status for each group;
						heroStatus = checkStatus(heroes);
						enemyStatus = checkStatus(enemies);
					}
				}			
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

	
	
	
	private static int selectTarget(DungeonCharacter[] order, int x)
	{
		// TODO Auto-generated method stub
		System.out.println("Select a target");
		for (int i = 0; i < order.length; i++)
		{
			if (i == x)
			{
				System.out.println(i + ": Self" + order[i].getHealth());
			}
			else
			{
				System.out.print(i + ": " + order[i].getName());
				if (order[i].isAlive())
				{
					System.out.println(" Health: " + order[i].getHealth());
					
				}
				else
					System.out.println(" Status: Dead");
			}
		}
		Scanner kb = new Scanner(System.in);
		int choice = kb.nextInt();
		while (choice > (order.length - 1) || choice < 1)
		{
			System.out.println("Invalid, pick again");
			choice = kb.nextInt();
		}
		return choice;
	}

	private static int selectAction(DungeonCharacter attacker)
	{
		int choice;
		if (attacker.isHero())
		{
			System.out.println("What will " + attacker.getName() + " do?");
			System.out.println("1) Attack \n2) Special Attack \n3) Nothing\n");
			Scanner kb = new Scanner(System.in);
			choice = kb.nextInt();
			while (choice > 3 || choice < 1)
			{
				System.out.println("Invalid, pick again");
				choice = kb.nextInt();
			}
		}
		else
		{
			choice = (int) (1 + Math.random() * 2);
		}
		return choice;
	}

	private static boolean checkStatus(DungeonCharacter[] dudes)
	{
		boolean temp = false;
		for (int x = 0; x < dudes.length; x++ )
		{
			temp = (dudes[x].isAlive() || temp);
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
		
		int[] intiative = new int[temp.length];
		for (int y = 0; y < intiative.length; y++)
		{
			intiative[y] = (int) (1 + (Math.random() * 20)); 
			intiative[y] += temp[y].getDex();
		}
		temp = MirrorSort(intiative, temp);
		return temp;
	}

	 private static DungeonCharacter[] MirrorSort(int[] initative, DungeonCharacter[] characters)
	  {
	  		 int sorter = 0;
	         int sorted = 0;
	         int place = 1;
	         DungeonCharacter holdCharacter;
	         int holdInitative;
	         while (place < initative.length-1)
	         {
	         	sorter = place;
					sorted = place-1;
		            while (initative[sorted] < initative[sorter])
		            {
		            	holdCharacter = characters[sorted];
		            	holdInitative = initative[sorted];
		            	characters[sorted] = characters[sorter];
		            	initative[sorted] = initative[sorter];
		            	characters[sorter] = holdCharacter;
		            	initative[sorter] = holdInitative;
		            	sorted--;
		            	sorter--;
						if (sorted < 0)
							sorted = sorter;
							
		            }	         
	            place++;
	         }
			return characters;
		}

}
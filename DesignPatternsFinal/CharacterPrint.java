package DesignPatternsFinal;

import java.util.Arrays;
import java.util.Scanner;

public class CharacterPrint
{
	private static CharacterPrint instance = null;
	private static Scanner myScanner;
	
	private CharacterPrint()
	{
		      // Exists only to defeat instantiation.
		if (myScanner == null)
		{
			myScanner = new Scanner(System.in);
		}
	}
	
	public static CharacterPrint getInstance()
	{
		if(instance == null) 
		{
			instance = new CharacterPrint();
		}
		return instance;
	}
	
	public void StatusPrint(DungeonCharacter[] heroes, String declaration)
	{
		System.out.println(declaration + ":");
        for(int i = 0; i < heroes.length; i++)
        {
            System.out.println(heroes[i].getName() + "  HP: " + heroes[i].getHealth() + "/" + heroes[i].getMaxHealth() + "\tSTAM: " + heroes[i].getStamina() + "/" + heroes[i].getMaxStamina());
        }
	}
	
	public int singleCharacterSelect(DungeonCharacter[] heroes)
	{
		System.out.println("Who would you like to select?");
		int choice = -1;
		while (choice < 0 || choice > heroes.length)
		{
			for(int i = 0; i < heroes.length; i++)
			{
				System.out.println((i + 1) + ") " + heroes[i].getName());
			}
			System.out.printf(">");
			choice = myScanner.nextInt();
			if ((choice - 1) < 0 || (choice - 1) > heroes.length)
			{
				System.out.println("Please Select Again");
			}
		}
		return choice - 1;
	}
	
	public int battleCharacterSelect(DungeonCharacter[] order, DungeonCharacter[] heroes, DungeonCharacter[] enemies, int x)
	{
		DungeonCharacter temp = order[x];
		int selector = Arrays.asList(heroes).indexOf(temp);
		int choice = -1;
		while(choice == -1)
		{
			System.out.println("Select a target");
			int i = 0;
			for (i = 0; i < enemies.length; i++)
			{
				System.out.print(i + 1 + ": " + enemies[i].getName());
				if (enemies[i].isAlive())
					System.out.println(" Status: Alive");
				else
					System.out.println(" Status: Dead");
			}
			System.out.println(i + 1 + ": Select Ally");
			System.out.printf(">");
			choice = myScanner.nextInt();
			
			while (choice > (enemies.length + 1) || choice < 1)
			{
				System.out.println("Invalid, pick again");
				choice = myScanner.nextInt();
			}
			if (choice != i + 1)
			{
				return Arrays.asList(order).indexOf(enemies[choice - 1]);
			}
			else
			{
				int j;
				for (j = 0; j < heroes.length; j++)
				{
					if (j == selector)
					{
						System.out.println(j + 1 + ": Self");
					}
					else
					{
						System.out.print(j + 1 + ": " + heroes[j].getName());
						if (heroes[j].isAlive())
							System.out.println(" Status: Alive");
						else
							System.out.println(" Status: Dead");
					}
				}
				System.out.println(j + 1 + ": Select Enemy");
				System.out.printf(">");
				choice = myScanner.nextInt();
				while (choice > (heroes.length + 1) || choice < 1)
				{
					System.out.println("Invalid, pick again");
					choice = myScanner.nextInt();
				}
				if (choice != j + 1)
				{
					return Arrays.asList(order).indexOf(heroes[choice - 1]);
				}
				else
				{
					choice = -1;
				}
			}
		}
		
		return choice - 1;
	}

	public static void printOrder(DungeonCharacter[] order, int x)
	{
		System.out.println();
		System.out.println("Order");
		if (order[x].isAlive())
		{
			for (int i = 0; i < order.length; i++)
			{
				if (i == x && order[i].isAlive())
				{
					System.out.print("<Current> " + order[i].getName() + ", ");
				}
				else if (i == (x + 1) && order[i].isAlive())
				{
					System.out.print("<Next> " + order[i].getName() + ", ");
				}
				else
					System.out.print(order[i].getName() + ", ");
			}
			System.out.println();
		}
		
	}
}

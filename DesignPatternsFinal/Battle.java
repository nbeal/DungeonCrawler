package DesignPatternsFinal;
import java.util.*;

import Enemies.EnemyFactory;

public class Battle
{
	DungeonCharacter[] heroes;
	DungeonCharacter[] enemies;
	Inventory inventory;
	public Battle(DungeonCharacter[] heroes, Inventory inventory)
	{
		this.heroes = heroes;
		this.inventory= inventory;
		
		CharacterFactory enFact = new EnemyFactory();
		DungeonCharacter monster1 = enFact.order("Blob");
		DungeonCharacter monster2 = enFact.order("Blob");
		DungeonCharacter monster3 = enFact.order("Blob");
		
		
		enemies = new DungeonCharacter[] {monster1, monster2, monster3};
	}
    public Battle(DungeonCharacter[] heroes, Inventory inventory, int boss) //Boss Battle
    {
        this.heroes = heroes;
        this.inventory= inventory;

        CharacterFactory enFact = new EnemyFactory();
        DungeonCharacter monster1 = enFact.order("Dread Lord");
        DungeonCharacter monster2 = enFact.order("Orc");
        DungeonCharacter monster3 = enFact.order("Orc");

        enemies = new DungeonCharacter[] {monster1, monster2, monster3};
    }

	
	public void printDescription()
	{
		System.out.print("A group of enemies appear!");

	}
	
	public boolean startBattle()
	{
		
		DungeonCharacter[] order;
		boolean heroStatus = checkStatus(heroes);
		boolean enemyStatus = checkStatus(enemies);
		while(heroStatus && enemyStatus)
		{
			order = concat(heroes, enemies);
			for (int x = 0; x < order.length; x++)
			{
				if (heroStatus && enemyStatus)
				{
					if (order[x].isAlive())
					{
						CharacterPrint.printOrder(order, x);
						int damage = 0;
						int item = 0;
						//Select Action
						
						if(order[x].isHero())
						{
							System.out.println("-----------------------------------------------------------------");
							CharacterPrint.getInstance().StatusPrint(heroes, "Heroes");
							System.out.println("-----------------------------------------------------------------");
							CharacterPrint.getInstance().StatusPrint(enemies, "Enemies");
							System.out.println("-----------------------------------------------------------------");
	
						}
						int action = selectAction(order[x]);
						
						if(action == 3)
						{
							
							inventory.printConsumables();
							item = inventory.selectItem(new Scanner(System.in));
						}
						
						//Select Target (print dead if dead)
						int target = selectTarget(order, heroes, x);
						//Damage Target
						if(action == 1)
						{
							damage = order[x].attack(order[target]);
						}
						else if(action == 2)
						{
							damage =  order[x].special(order[target]);
						}
						else if(action == 3)
						{
							
							System.out.print(order[target].getName() + " ");
							inventory.consume(item - 1, order[target]);
							
						}
							if(action != 3)
								System.out.println(order[x].getName() + " hit " + order[target].getName() + " for " + damage + " damage!" );
							//check status for each group;
							heroStatus = checkStatus(heroes);
							enemyStatus = checkStatus(enemies);

					}
				}			
			}
			
			reduceAllConsumables(order);
			
		}
		if (heroStatus)
		{
			System.out.println("You won the battle!");
			int expGained = 0;
			for (int i = 0; i < enemies.length; i++)
			{
				expGained += enemies[i].getExp();
			}
			System.out.println("You gained " + expGained + " experience");
			for (int i = 0; i < heroes.length; i++)
			{
				if (heroes[i].isAlive())
					heroes[i].gainExperience(expGained);
			}
			return true;
		}
		else
		{
			System.out.println("--GAME OVER--");
			return false;
		}
	}

	
	
	
	private int selectTarget(DungeonCharacter[] order, DungeonCharacter[] heroes, int x)
	{
		// TODO Auto-generated method stub
		if (order[x].isHero())
		{
			return CharacterPrint.getInstance().battleCharacterSelect(order, heroes, enemies, x);
		}
		else
		{
			int choice = (int) ( Math.random() * heroes.length);
			while(!heroes[choice].isAlive())
			{
				choice = (int) ( Math.random() * heroes.length);
			}
			
			return Arrays.asList(order).indexOf(heroes[choice]);
			
		}
	}

	private int selectAction(DungeonCharacter attacker)
	{
		int choice = -1;
        Scanner kb = new Scanner(System.in);
		if (attacker.isHero())
		{
			System.out.println("\nWhat will " + attacker.getName() + " do?		Hp:" + attacker.getHealth() + " Stamina:" + attacker.getStamina());
			System.out.println("1) " + attacker.attacktype.getAttackName()+ " \n2) " + attacker.special.getAttackName() + "\n3) Use Item\n");
            while(choice < 1 || choice > 3) {
                try {
                    System.out.printf(">");

                    choice = kb.nextInt();


                    if (choice == 2) {
                        if (attacker.getStamina() < attacker.special.getStamUsed()) {
                            System.out.println("Not enough stamina!");
                            choice = 10;
                        }
                    }

                    if (choice == 3) {
                        if (!inventory.haveConsumables()) {
                            System.out.println("You have no consumable items!");
                            choice = 10;
                        }
                    }

                    while ((choice > 3 || choice < 1)) {

                        System.out.println("Invalid, pick again");
                        System.out.printf(">");
                        choice = kb.nextInt();

                        if (choice == 2 && (attacker.getStamina() < attacker.special.getStamUsed())) {
                            System.out.println("Not enough stamina!");
                            choice = 10;
                        }

                        if (choice == 3) {
                            if (!inventory.haveConsumables()) {
                                System.out.println("You have no consumable items!");
                                choice = 10;
                            }
                        }
                    }
                } catch (InputMismatchException e) {
                    if (kb.nextLine().toLowerCase().equals("exit")) {
                        System.out.printf("\nExiting...\n");
                        System.exit(1);
                    }
                    System.out.printf("\nInvalid choice! Please try again\n");
                }
            }
		}
		else
		{
            if(attacker.getName().equals("Dread Lord"))
            {
                choice = (int) (1 + Math.random() * 3);
                return choice;
            }
			choice = (int) (1 + Math.random() * 2);
		}
		return choice;
	}

	private boolean checkStatus(DungeonCharacter[] dudes)
	{
		boolean temp = false;
		for (int x = 0; x < dudes.length; x++ )
		{
			temp = (dudes[x].isAlive() || temp);
		}
		return temp;
	}

	private DungeonCharacter[] concat(DungeonCharacter[] heroes, DungeonCharacter[] enemies)
	{
		
		DungeonCharacter[] temp = new DungeonCharacter[heroes.length + enemies.length];
		int x = 0;
		for (int i = 0; i < heroes.length; i++)
		{
			temp[x] = heroes[i];
			x++;
		}
		for (int i = 0; i < enemies.length; i++)
		{
			if (enemies[i].isAlive())
			{
				temp[x] = enemies[i];
				x++;
			}
		}
		
		int[] initiative = new int[temp.length];
		for (int y = 0; y < initiative.length; y++)
		{
			initiative[y] = (int) (1 + (Math.random() * 20));
			initiative[y] += temp[y].getDex();
		}
		temp = MirrorSort(initiative, temp);
		return temp;
	}

	 private DungeonCharacter[] MirrorSort(int[] initiative, DungeonCharacter[] characters)
	  {
	  		 int sorter = 0;
	         int sorted = 0;
	         int place = 1;
	         DungeonCharacter holdCharacter;
	         int holdInitiative;
	         while (place < initiative.length-1)
	         {
	         	sorter = place;
					sorted = place-1;
		            while (initiative[sorted] < initiative[sorter])
		            {
		            	holdCharacter = characters[sorted];
		            	holdInitiative = initiative[sorted];
		            	characters[sorted] = characters[sorter];
		            	initiative[sorted] = initiative[sorter];
		            	characters[sorter] = holdCharacter;
		            	initiative[sorter] = holdInitiative;
		            	sorted--;
		            	sorter--;
						if (sorted < 0)
							sorted = sorter;
							
		            }	         
	            place++;
	         }
			return characters;
		}
	 
	 public void reduceAllConsumables(DungeonCharacter[] order)
	 {
		 for(int k = 0; k < order.length; k++)
		 {
			 order[k].reduceConsumables();
		 }
	 }

    public int startBossBattle()
    {

        DungeonCharacter[] order = concat(heroes, enemies);
        boolean heroStatus = checkStatus(heroes);
        boolean enemyStatus = checkStatus(enemies);

        System.out.println("You open the large golden doors and enter into a large room. Perhaps once grand with gold and gem in the walls and treasure scattered about, it is now a shadow of it once was, with dust, cobwebs, and crumbling stonework everywhere. At the other end of the room is a large stone throne, and on it is what you came to this crypt to rid of... The Dread Lord.");
        System.out.println("The ancient skeleton rises from the throne to a height of 10 ft, the old chain mail jingling and a scraping as he lifts a large battleaxe. \"You have disturbed my slumber... now you will die!\" ");
        System.out.println("--PREPARE FOR BATTLE!--");
        while(heroStatus && enemyStatus)
        {
            for (int x = 0; x < order.length; x++)
            {
                CharacterPrint.printOrder(order, x);
                if (heroStatus && enemyStatus)
                {
                    if (order[x].isAlive())
                    {
                        int damage = 0;
                        int item = 0;
                        //Select Action

                        if(order[x].isHero())
                        {
                            System.out.println("-----------------------------------------------------------------");
                            CharacterPrint.getInstance().StatusPrint(heroes, "Heroes");
                            System.out.println("-----------------------------------------------------------------");
                            CharacterPrint.getInstance().StatusPrint(enemies, "Enemy");
                            System.out.println("-----------------------------------------------------------------");

                        }
                        int action = selectAction(order[x]);

                        if(action == 3 && order[x].isHero())
                        {

                            inventory.printConsumables();
                            item = inventory.selectItem(new Scanner(System.in));
                        }

                        //Select Target (print dead if dead)
                        int target = selectTarget(order, heroes, x);
                        //Damage Target
                        if(action == 1)
                        {
                            damage = order[x].attack(order[target]);
                        }
                        else if(action == 2)
                        {
                            damage =  order[x].special(order[target]);
                        }
                        else if(action == 3)
                        {
                            if (!order[x].isHero())
                            {
                                damage =  order[x].bossSpecial(order[target]);
                                System.out.println(order[x].getName() + " hit " + order[target].getName() + " for " + damage + " damage!" );
                            }
                            else {
                                System.out.print(order[target].getName() + " ");
                                inventory.consume(item - 1, order[target]);
                            }
                        }
                        if(action != 3)
                            System.out.println(order[x].getName() + " hit " + order[target].getName() + " for " + damage + " damage!" );
                        //check status for each group;
                        heroStatus = checkStatus(heroes);
                        enemyStatus = checkStatus(enemies);

                    }
                }
            }

            reduceAllConsumables(order);

        }
        if (heroStatus)
        {

            int expGained = 0;
            for (int i = 0; i < enemies.length; i++)
            {
                expGained = enemies[i].getExp();
            }
            System.out.println("You gained " + expGained + " experience");
            for (int i = 0; i < heroes.length; i++)
            {
                if (heroes[i].isAlive())
                    heroes[i].gainExperience(expGained);
            }
            System.out.println("Congratulations! You have vanquished the evil of the Dread Lord!");
            return 1;
        }
        else
        {
            System.out.println("--GAME OVER--");
            return 0;
        }
    }


}
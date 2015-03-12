package DesignPatternsFinal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Equipment.Equipment;
import Heroes.Hero;
import Items.ConsumableItem;
import Items.Item;

public class Inventory 
{
	private ArrayList<Item> consumables = new ArrayList<Item>();
	private ArrayList<Item> equipments = new ArrayList<Item>();
	private int key;
	
	private static Inventory playerInventory;
	
	int numConsumables = 0;
	int numEquipment = 0;
	
	private Inventory()
	{
		key = 0;
	}
	
	public static Inventory getInventory()
	{
		if(playerInventory == null)
		{
			return new Inventory();
		}
		
		return playerInventory;
	}
	
	public void equipPartyMember(Hero hero, int index)
	{
		Equipment equipment = (Equipment)equipments.remove(index);
		Equipment old = hero.equip(equipment);
		equipments.add(old);
	
	}
	
	public void addItem(Item item)
	{
		if(item.isConsumable())
			consumables.add(item);
		else
			equipments.add(item);
		
	}
	
	public void addKey()
	{
		key++;
	}
	
	public int getKeys()
	{
		return key;
	}
	
	public void consume(int index, DungeonCharacter entity)
	{
		ConsumableItem item = (ConsumableItem)consumables.remove(index);
		
		item.consume(entity);
		item.consumeMessage();
	}
	
	public int selectItem(Scanner input)
	{
		int choice = -1;

        while(choice < 1 || choice > consumables.size())
        {
            try {
                System.out.printf(">");
                choice = input.nextInt();
                if(choice == 0)
                    return 0;
                if(choice < 1 || choice > consumables.size()) {
                    System.out.printf("\nInvalid choice! Please try again\n");
                    input.nextLine();
                }
            }catch(InputMismatchException e)
            {
                input.nextLine();
                System.out.printf("\nInvalid choice! Please try again\n");
            }
        }
        input.nextLine();
        return choice;
	}
	
	private int selectEquip(Scanner input)
	{
        int choice = -1;

        while(choice < 1 || choice > equipments.size())
        {
            try {
                System.out.printf(">");
                choice = input.nextInt();
                if(choice == 0)
                    return 0;
                if(choice < 1 || choice > equipments.size()) {
                    System.out.printf("\nInvalid choice! Please try again\n");
                    input.nextLine();
                }
            }catch(InputMismatchException e)
            {
                input.nextLine();
                System.out.printf("\nInvalid choice! Please try again\n");
            }
        }
        input.nextLine();
        return choice;
	}
	
	public boolean haveConsumables()
	{
		return(consumables.size() > 0);
	}
	
	public boolean haveEquipment()
	{
		return(equipments.size() > 0);
	}
	
	public void printConsumables()
	{	
		if(haveConsumables())
		{
			for(int k = 0; k < consumables.size(); k++)
			{
				System.out.println(k+1 + ") " + consumables.get(k).getName());
			}
			
		}
		else
			System.out.println("No consumable items!");
	}
	
	public void printEquipment()
	{	
		if (haveEquipment())
		{
			for(int k = 0; k < equipments.size(); k++)
			{
				System.out.println(k+1 + ") " + equipments.get(k).getName());
			}
		}
	}
	
	public void useItems(DungeonCharacter[] heroes)
	{
		int choice = -1;
		Scanner kb = new Scanner(System.in);
        
		while (choice != 0)
		{
            try {
            	System.out.println("Would you like to use an item or equip something?\n1)Item\n2)Equip\n0)Exit");
                System.out.printf(">");
                choice = kb.nextInt();
                if (choice == 0)
                    return;
                if (choice == 1 && haveConsumables()) {
                    System.out.println("Items in Inventory:");
                    printConsumables();
                    System.out.println("0) Exit");

                    int item = (selectItem(kb) - 1);
                    if(item == -1)
                    {
                        choice = 0;
                        return;
                    }
                    else {
                        int hero = CharacterPrint.getInstance().singleCharacterSelect(heroes);
                        consume(item, heroes[hero]);
                    }
                } else if (choice == 2) {
                    System.out.println("Equipment in Inventory:");
                    if(!haveEquipment()) {
                        System.out.println("No Equipment!");
                        return;
                    }
                    printEquipment();
                    System.out.println("0) Exit");
                    int item = (selectEquip(kb) - 1);
                    if(item == -1)
                    {
                        choice = 0;
                        return;
                    }
                    else {
                        int hero = CharacterPrint.getInstance().singleCharacterSelect(heroes);
                        equipPartyMember((Hero) heroes[hero], item);
                    }
                }
            }catch(InputMismatchException e)
            {
                kb.nextLine();
                System.out.printf("\nInvalid choice! Please try again\n");
            }
		}
	}

	
}

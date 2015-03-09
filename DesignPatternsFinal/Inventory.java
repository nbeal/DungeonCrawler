package DesignPatternsFinal;

import java.util.ArrayList;
import java.util.Scanner;

import Equipment.Equipment;
import Heroes.Hero;
import Items.ConsumableItem;
import Items.Item;

public class Inventory 
{
	private ArrayList<Item> consumables = new ArrayList<Item>();
	private ArrayList<Item> equipments = new ArrayList<Item>();
	
	private static Inventory playerInventory;
	
	int numConsumables = 0;
	int numEquipment = 0;
	
	private Inventory(){};
	
	public static Inventory getInventory()
	{
		if(playerInventory == null)
			return new Inventory();
		
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
	
	public void consume(int index, DungeonCharacter entity)
	{
		ConsumableItem item = (ConsumableItem)consumables.remove(index);
		
		item.consume(entity);
		item.consumeMessage();
	}
	
	public int selectItem(Scanner input)
	{
		System.out.printf(">");
		int choice = input.nextInt();
		
		while(choice < 1 || choice > consumables.size())
		{
			System.out.println("Invalid, pick again");
			choice = input.nextInt();
		}
		
		return choice;
	}
	
	private int selectEquip(Scanner input)
	{
		System.out.printf(">");
		int choice = input.nextInt();
		
		while(choice < 1 || choice > equipments.size())
		{
			System.out.println("Invalid, pick again");
			choice = input.nextInt();
		}
		
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
		else
			System.out.println("No Equipment!");
	}
	
	public void useItems(DungeonCharacter[] heroes)
	{
		int choice = -1;
		Scanner kb = new Scanner(System.in);
		while (choice != 0)
		{
			System.out.println("Would you like to use an item or equip something?\n1)Item\n2)Equip\n0)Exit");
			System.out.printf(">");
			choice = kb.nextInt();
			if (choice == 0)
				return;
			if (choice == 1 && haveConsumables())
			{
				System.out.println("Items in Inventory:");
				printConsumables();
				
				int item = (selectItem(kb) - 1);
				System.out.println("Who do you want to use it on?");
				int hero = CharacterPrint.getInstance().singleCharacterSelect(heroes);
				consume(item,heroes[hero]);
			}
			else if(choice == 2 && haveEquipment())
			{
				System.out.println("Equipment in Inventory:");
				printEquipment();
				int item = (selectEquip(kb) - 1);
				System.out.println("Who do you want to equip it to?");
				int hero = CharacterPrint.getInstance().singleCharacterSelect(heroes);
				equipPartyMember((Hero) heroes[hero],item);
			}
		}
	}

	
}

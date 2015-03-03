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
		
		item.consumeMessage();
		item.consume(entity);
	}
	
	public int selectItem(Scanner input)
	{
		int choice = input.nextInt();
		
		while(choice < 1 || choice > consumables.size())
		{
			System.out.println("Invalid, pick again");
			choice = input.nextInt();
		}
		
		return choice;
	}
	
	public boolean haveConsumables()
	{
		if(consumables.size() > 0)
			return true;
		
		return false;
	}
	
	public void printConsumables()
	{	
		if(consumables.size() > 0)
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
		for(int k = 0; k < equipments.size(); k++)
		{
			System.out.println(k+1 + ") " + equipments.get(k).getName());
		}
	}
	
}

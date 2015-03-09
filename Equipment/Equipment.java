package Equipment;

import DesignPatternsFinal.DamageHandler;
import Items.Item;

public class Equipment implements Item
{
	private int hitPoints;
	private int strength;
	private int[] attack;
	private int dexterity;
	private int stamina;
	private int defense;
	private int[] defenses;
	String name;
	String type;
	
	public Equipment()
	{
		this.hitPoints = 0;
		this.strength = 0;
		this.attack = DamageHandler.fillArray("");
		this.dexterity = 0;
		this.stamina = 1;
		this.defense = 0;
		this.defenses = DamageHandler.fillArray("");
		this.name = "Nothing";
		this.type = "Nothing";
	}
	
	public Equipment(int hp, int str, int dex, int stam, int def, String tempName)
	{
		this.hitPoints = hp;
		this.strength = str;
		this.dexterity = dex;
		this.stamina = stam;
		this.defense = def;
		this.defenses = DamageHandler.fillArray("");
		this.name = tempName;
	}
	
	//constructor for armor type equipment
	public void RedoEquipment(int hp, int str, int dex, int stam, String def, String tempName)
	{
		this.hitPoints = hp;
		this.strength = str;
		this.dexterity = dex;
		this.stamina = stam;
		this.defense = 0;
		this.defenses = DamageHandler.fillArray(def);
		this.name = tempName;
	}
	
	//constructor for weapon type equipment
	public void RedoEquipment(int hp, int str, String atk , int dex, int stam, String tempName)
	{
		this.hitPoints = hp;
		this.strength = str;
		this.attack = DamageHandler.fillArray(atk);
		this.dexterity = dex;
		this.stamina = stam;
		this.defense = 0;
		this.name = tempName;
	}
	
	public int getDef()
	{
		return defense;
	}
	
	public int[] getDefenses()
	{
		return defenses;
	}
	
	public int[] getAttack()
	{
		return attack;
	}
	
	public String getType()
	{
		return type;
	}

	public int getDex() 
	{
		return this.dexterity;
	}

	public int getStrength() 
	{
		return this.strength;
	}

	@Override
	public boolean isConsumable() 
	{
		return false;
	}

	@Override
	public String getName() 
	{
		return this.name;
	}

	public int getStamina()
	{
		return this.stamina;
	}
}
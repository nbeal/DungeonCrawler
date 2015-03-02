package DesignPatternsFinal;
//Dungeon Character class written by Nicholas Valentine

import Attack.AttackType;
import Attack.SpecialAttack;
import Equipment.Equipment;
import DesignPatternsFinal.DamageHandler;

public abstract class DungeonCharacter
{
	private int hitPoints;
	private int strength;
	private int dexterity;
	private int stamina;
	private int[] defenses;
	private String name;
	
	protected AttackType attacktype;
	protected SpecialAttack special;
	
	
	protected Equipment head;
	protected Equipment torso;
	protected Equipment hands;
	protected Equipment feet;
	protected Equipment legs;
	protected Equipment weapon;
	
	public DungeonCharacter()
	{
		this.hitPoints = 0;
		this.strength = 0;
		this.dexterity = 0;
		this.stamina = 0;
		
		this.defenses = DamageHandler.fillArray("");
		
		this.name = "Nothing";
	}
	
	public DungeonCharacter(String enName, Stats statistics)
	{
		int[] stats = statistics.getStats();
		
		this.hitPoints = stats[0];
		this.strength = stats[1];
		this.dexterity = stats[2];
		this.stamina = stats[3];
		
		String def = "" + DamageHandler.getInstance().DAMAGE_NORMAL;
		def = def + "," + stats[4];
		defenses = DamageHandler.fillArray(def);
		
		this.name =enName;

	}
	
	public DungeonCharacter(int hp, int str, int dex, int stam, int def, String tempName)
	{
		this.hitPoints = hp;
		this.strength = str;
		this.dexterity = dex;
		this.stamina = stam;
		
		String sdef = "" + DamageHandler.getInstance().DAMAGE_NORMAL;
		sdef = sdef + "," + def;
		defenses = DamageHandler.fillArray(sdef);
		this.name = tempName;
	}

	
	public int attack(DungeonCharacter defender)
	{
		String attack = attacktype.attack();
		int[] damage = DamageHandler.fillArray(attack);
		damage = DamageHandler.addToArray(damage, weapon.getAttack());
		return defender.modifyHealth(damage);
		//return attacktype.attack(defender);
	}
	
	public int special(DungeonCharacter defender)
	{		
		int stamUsed = special.getStamUsed();
		if(getStamina() >= stamUsed)
		{
			modifyStamina(stamUsed);
			//return special.attack(defender);
			
			String attack = special.attack();
			int[] damage = DamageHandler.fillArray(attack);
			return defender.modifyHealth(damage);
			
		}
		System.out.println("Not enough stamina to use");
		return 1234567;
		
	}
	
	public int modifyHealth(int[] damage)
	{
		int[] mydefense = totalDefense();
		int hitDamage = DamageHandler.damageCalculation(mydefense , damage);
		this.hitPoints -= hitDamage;
		
		if(this.hitPoints < 0)
			this.hitPoints = 0;
		return hitDamage;
	}
	
	private int[] totalDefense()
	{
		int[] total;
		total = defenses.clone();
		total = DamageHandler.addToArray(total, head.getDefenses());
		total = DamageHandler.addToArray(total, torso.getDefenses());
		total = DamageHandler.addToArray(total, hands.getDefenses());
		total = DamageHandler.addToArray(total, feet.getDefenses());
		total = DamageHandler.addToArray(total, legs.getDefenses());
		return total;
	}

	public void modifyStamina(int stamUsed)
	{
		stamina -= stamUsed;

	}
	
	public int getStamina()
	{
		return stamina;
	}
	
	public int getHealth()
	{
		return hitPoints;
	}
	
	public boolean isAlive()
	{
		if(getHealth() == 0)
			return false;
			
		return true;
	}
	
	/*public int calcTotalDef()
	{
		return defense + head.getDef() + torso.getDef() + hands.getDef() + legs.getDef() + feet.getDef();
	}*/
	
	public Equipment equip(Equipment item)
	{
		String type = item.getType();
		Equipment old = null;
		if (requirementsMet(item))
		{
			if(type.equals("head"))
			{
				old = head;
				head = item;
			}
			else if(type.equals("torso"))
			{
				old = torso;
				torso = item;	
			}
			else if(type.equals("hands"))
			{
				old = hands;
				hands = item;
			}
			else if(type.equals("legs"))
			{
				old = legs;
				legs = item;
			}
			else if(type.equals("feet"))
			{
				old = feet;
				feet = item;		
			}
			else if(type.equals("weapon"))
			{
				old = weapon;
				weapon = item;
			}
		}
		else
		{
			System.out.println("Requirements not met to equip this item");
		}
		return old;
			
	}
	
	private boolean requirementsMet(Equipment item)
	{
		// TODO Auto-generated method stub
		if (this.dexterity >= item.getDex())
		{
			if (this.strength >= item.getStrength())
			{
				return true;
			}
		}
		return false;
	}

	public boolean isHero()
	{
		return false;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getDex()
	{
	
		return dexterity;
	}
}

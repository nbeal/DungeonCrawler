package DesignPatternsFinal;
//Dungeon Character class written by Nicholas Valentine

import java.util.ArrayList;
import java.util.Scanner;

import Attack.AttackType;
import Attack.SpecialAttack;
import Equipment.Equipment;
import Equipment.NullEquipment;
import Items.OverTimeItem;
import DesignPatternsFinal.DamageHandler;

public abstract class DungeonCharacter
{
	private int MaxHealth;
	private int hitPoints;
	private int strength;
	private int dexterity;
	private int stamina;
    private int MaxStamina;
	private int[] defenses;
	private String name = "";
	private String title;
	
	protected AttackType attacktype;
	protected SpecialAttack special;
    protected SpecialAttack bossSpecial;
	
	
	protected Equipment head = new NullEquipment();
	protected Equipment torso = new NullEquipment();
	protected Equipment hands = new NullEquipment();
	protected Equipment feet = new NullEquipment();
	protected Equipment legs = new NullEquipment();
	protected Equipment weapon = new NullEquipment();
	
	private int experience;
	private int nextLevel;
	private int currentLevel;
	private int points;
	
	ArrayList<OverTimeItem> consumablesInUse = new ArrayList<OverTimeItem>();
	
	public DungeonCharacter()
	{
		this.MaxHealth = 0;
		this.hitPoints = 0;
		this.strength = 0;
		this.dexterity = 0;
		this.stamina = 0;
        this.MaxStamina = 0;
		
		this.defenses = DamageHandler.fillArray("");
		
		this.name = "Nothing";
		this.title = "The placeholder";
		this.experience = 0;
		this.nextLevel = 20;
		this.currentLevel = 1;
		this.points = 0;
	}
	
	public DungeonCharacter(String enName, Stats statistics)
	{
		int[] stats = statistics.getStats();
		
		this.MaxHealth = stats[0];
		this.hitPoints = stats[0];
		this.strength = stats[1];
		this.dexterity = stats[2];
		this.stamina = stats[3];
        this.MaxStamina = stats[3];
		
		String def = "" + DamageHandler.getInstance().DAMAGE_NORMAL;
		def = def + "," + stats[4];
		defenses = DamageHandler.fillArray(def);
		
		this.title = enName;
		
		this.experience = 0;
		this.nextLevel = 20;
		this.currentLevel = 1;
		this.points = 0;

	}
	
	public DungeonCharacter(int hp, int str, int dex, int stam, int def, String tempName)
	{
		this.MaxHealth = hp;
		this.hitPoints = hp;
		this.strength = str;
		this.dexterity = dex;
		this.stamina = stam;
        this.MaxStamina = stam;
		
		String sdef = "" + DamageHandler.getInstance().DAMAGE_NORMAL;
		sdef = sdef + "," + def;
		defenses = DamageHandler.fillArray(sdef);
		this.name = tempName;
		
		this.experience = 0;
		this.nextLevel = 20;
		this.currentLevel = 1;
		this.points = 0;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public int attack(DungeonCharacter defender)
	{
		String attack = attacktype.attack();
		int[] damage = DamageHandler.fillArray(attack);
		if (damage[DamageHandler.DAMAGE_HEAL] != 0)
		{
			damage = DamageHandler.addToArray(damage, weapon.getAttack());
		}
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

    public int bossSpecial(DungeonCharacter defender)
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
        //System.out.println("Not enough stamina to use");
        return 1234567;

    }
	
	public int modifyHealth(int[] damage)
	{
		int[] mydefense = totalDefense();
		int hitDamage = DamageHandler.damageCalculation(mydefense , damage);
		this.hitPoints -= hitDamage;
		
		if(this.hitPoints < 0)
			this.hitPoints = 0;
		
		else if (this.hitPoints > this.MaxHealth)
		{
			this.hitPoints = this.MaxHealth;
		}
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
		stamina -= (stamUsed * staminaReduction());

	}
	
	private double staminaReduction()
	{
		double reduction = 1;
		reduction *= head.getStamina();
		reduction  *= torso.getStamina();
		reduction *= hands.getStamina();
		reduction *= feet.getStamina();
		reduction *= legs.getStamina();
		return reduction;
	}

	public int getStamina()
	{
		return stamina;
	}
	
	public int getHealth()
	{
		return hitPoints;
	}
	
	public void modifyStrength(int s)
	{
		strength += s;
	}
	
	public void modifyDexterity(int d)
	{
		dexterity += d;
	}
	
	public void addConsumableInUse(OverTimeItem item)
	{
		consumablesInUse.add(item);
	}
	
	public void removeConsumableInUse(OverTimeItem item)
	{
		consumablesInUse.remove(item);
	}
	
	public void reduceConsumables()
	{
		boolean usedUp = false;
		for(int k = 0; k < consumablesInUse.size(); k++)
		{
			usedUp = consumablesInUse.get(k).reduceTime();
			
			if(usedUp)
			{
				consumablesInUse.get(k).undo(this);
			}
		}
	}
	
	public boolean isAlive()
	{
		if(getHealth() == 0)
			return false;
			
		return true;
	}
	
	public Equipment equip(Equipment item)
	{
		String type = item.getType();
		Equipment old = item;
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
				this.attacktype = item.getAttackType();
			}
		}
		else
		{
			System.out.println("Requirements not met to equip this item");
			return item;
		}
		System.out.println("You have equipped " + item.getName());
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
		if(name.equals(""))
			return title;
		return name + " The " + title;
	}
	
	public int getDex()
	{
		return dexterity;
	}

	public void heal(int i) 
	{
		this.hitPoints += i;
		if (this.hitPoints > this.MaxHealth)
		{
			this.hitPoints = this.MaxHealth;
		}
	}
	
	public int getExp()
	{
		return this.experience;
	}
	
	public void setExp(int exp)
	{
		this.experience = exp;
	}
	
	public void gainExperience(int exp)
	{
		this.experience += exp;
		while (this.experience >= this.nextLevel)
		{
			this.currentLevel++;
			System.out.println(this.name + " has gained a level, they are now level " + this.currentLevel);
			this.points++;
			this.experience -= this.nextLevel;
			this.nextLevel += 20;
		}
	}
	
	public void spendPoint()
	{
		boolean spendPoints = true;
		if (this.points == 0)
		{
			System.out.println(this.name + " has no points to spend");
		}
		while (this.points > 0 && spendPoints)
		{
			System.out.println();
			int choice = 0;
			stats();
			System.out.println(this.name + " has " + this.points + " to spend");
			System.out.println("Which would you like to increase?");
			System.out.println("1) Health (20 point increase) \n2) Stength (1 point increase) \n3) Dexterity (1 point increase) \n4) Defense (1 point increase) \n5) Cancel");
			System.out.printf(">");
			Scanner kb = new Scanner(System.in);
			choice = kb.nextInt();
						
			switch (choice)
			{
				case 1:
					//health
					this.MaxHealth += 20;
					this.hitPoints += 20;
					System.out.println("Health increased by 20");
					this.points--;
					break;
				case 2:
					strength++;
					System.out.println("Stength increased by 1");
					this.points--;
					break;
				case 3:
					dexterity++;
					System.out.println("Dexterity increased by 1");
					this.points--;
					break;
				case 4:
					defenses = DamageHandler.addToArray(defenses, "" + DamageHandler.DAMAGE_NORMAL + "," + 1);
					System.out.println("Defense increased by 1");
					this.points--;
					break;
				default:
					System.out.println("Nothing was increased");
					spendPoints = false;
			}
		}
	}

	public void stats()
	{
		// TODO Auto-generated method stub
		System.out.println("Max Health: " + this.MaxHealth);
		System.out.println("Stength: " + this.strength);
		System.out.println("Dexterity: " + this.dexterity);
		System.out.println("current Stamina: " + this.stamina);
		System.out.println("Defense: " + this.defenses[DamageHandler.DAMAGE_NORMAL]);
	}

	public int getMaxHealth()
	{
		return this.MaxHealth;
	}
	
	public void printEquipment()
	{
		System.out.println("Head: " + head.getName());
		System.out.println("Torso: " + torso.getName());
		System.out.println("hands: " + hands.getName());
		System.out.println("legs: " + legs.getName());
		System.out.println("feet: " + feet.getName());
		System.out.println("Weapon: " + weapon.getName());
	}

    public int getMaxStamina()
    {
        return this.MaxStamina;
    }

}

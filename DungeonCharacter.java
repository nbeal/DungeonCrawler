//Dungeon Character class written by Nicholas Valentine

public abstract class DungeonCharacter
{
	private int hitPoints;
	private int strength;
	private int dexterity;
	private int stamina;
	private int defense;
	//etc)
	private String name;
	
	AttackType attacktype;
	SpecialAttack special;
	
	
	Equipment head;
	Equipment torso;
	Equipment hands;
	Equipment feet;
	Equipment legs;
	
	
	public DungeonCharacter()
	{
		this.hitPoints = 0;
		this.strength = 0;
		this.dexterity = 0;
		this.stamina = 0;
		this.defense = 0;
		this.name = "derp";
	}
	
	public DungeonCharacter(int hp, int str, int dex, int stam, int def, String tempName)
	{
		this.hitPoints = hp;
		this.strength = str;
		this.dexterity = dex;
		this.stamina = stam;
		this.defense = def;
		this.name = tempName;
	}
	
	public void attack(DungeonCharacter defender)
	{
		attacktype.attack(defender);
	}
	
	public void special(DungeonCharacter defender)
	{
		special.SpecialAttack(defender);
	}
	
	public void modifyHealth(int damage)
	{
		this.hitPoints -= damage;
		
		if(this.hitPoints < 0)
			this.hitPoints = 0;
	}
	
	public void reactToAction(int damage)
	{
		int realDamage;
		
		if(damage < 0)
			this.modifyHealth(damage);
		else
		{
			realDamage = damage - this.calcTotalDef();
			
			if(realDamage > 0)
				this.modifyHealth(realDamage);
		}
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
	
	public int calcTotalDef()
	{
		return defense + head.getDef() + torso.getDef() + hands.getDef() + legs.getDef() + feet.getDef();
	}
	
	public void equip(Equipment item)
	{
		String type = item.getType();
		
		if(type.equals("head"))
			head = item;
		else if(type.equals("torso"))
					torso = item;
		else if(type.equals("hands"))
					hands = item;
		else if(type.equals("legs"))
					legs = item;
		else if(type.equals("feet"))
					feet = item;
			
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

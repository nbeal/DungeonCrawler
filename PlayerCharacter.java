import java.util.*;

public class PlayerCharacter extends Hero
{
	ArrayList<Equipment> inventory;
	
	private static PlayerCharacter uniqueInstance;
	
	private PlayerCharacter()
	{
		super();
	}
	
	private PlayerCharacter(int hp, int str, int dex, int stam, int def, String tempName)
	{
		super(hp, str, dex, stam, def, tempName);
		
		inventory  = new ArrayList<Equipment>();
	}
	
	public static PlayerCharacter getInstancePlayer(int hp, int str, int dex, int stam, int def, String tempName)
	{
		if(uniqueInstance == null)
			uniqueInstance = new PlayerCharacter(hp, str, dex, stam, def, tempName);
		return uniqueInstance;
	}
	
	public boolean isHero()
	{
		return true;
	}
	
	public void equipPartyMember(Hero hero, int index)
	{
		Equipment equipment = inventory.remove(index);
		Equipment old = hero.equip(equipment);
		inventory.add(old);
	
	}

}
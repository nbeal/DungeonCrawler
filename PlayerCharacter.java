import java.util.*;

public class PlayerCharacter extends Hero
{
	ArrayList inventory;
	
	private static PlayerCharacter uniqueInstance;
	
	private PlayerCharacter()
	{
		super();
	}
	
	private PlayerCharacter(int hp, int str, int dex, int stam, int def, String tempName)
	{
		super(hp, str, dex, stam, def, tempName);
		
		inventory  = new ArrayList();
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
	
	public void equipPartyMember(Hero hero, Equipment equipment)
	{
		
	
	}

}
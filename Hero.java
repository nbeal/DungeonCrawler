public class Hero extends DungeonCharacter
{

	protected Hero()
	{
		super();
	}
	
	protected Hero(int hp, int str, int dex, int stam, int def, String tempName)
	{
		super(hp, str, dex, stam, def, tempName);
		
	}
	
	public boolean isHero()
	{
		return true;
	}
	
	public Equipment equip(Equipment item)
	{
		String type = item.getType();
		Equipment old;
		
		if(type.equals("head"))
		{
			old = head;
			head = item;
			return old;
		}
		else if(type.equals("torso"))
				{
					old = torso;
					torso = item;
					return old;
				}
		else if(type.equals("hands"))
				{
					old = hands;
					hands = item;
					return old;
				}
		else if(type.equals("legs"))
				{
					old = legs;
					legs = item;
					return old;
				}
		else if(type.equals("feet"))
				{
					old = feet;
					feet = item;
					return old;
				}
		else
			return null;
	}

}
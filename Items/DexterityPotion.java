package Items;

import DesignPatternsFinal.DungeonCharacter;

public class DexterityPotion implements OverTimeItem
{
	
	int timeLeft = 2;
	public void consume(DungeonCharacter entity) 
	{
		entity.modifyDexterity(10);
		entity.addConsumableInUse(this);
	}

	
	public void consumeMessage() 
	{
		
		System.out.println("gained 10 dexterity!");
	}

	
	public boolean isConsumable() 
	{
		return true;
	}

	
	public String getName() 
	{
		return "Dexterity Potion";
	}

	
	public void undo(DungeonCharacter entity) 
	{
		
		entity.modifyStrength(-10);
		entity.removeConsumableInUse(this);
		System.out.println(entity.getName() + "'s Dexterity Potion wore off!");
	}

	
	public boolean reduceTime() 
	{
		timeLeft--;
		if(timeLeft == 0)
			return true;
		return false;
	}

}

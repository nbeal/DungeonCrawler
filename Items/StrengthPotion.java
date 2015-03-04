package Items;

import DesignPatternsFinal.DungeonCharacter;

public class StrengthPotion implements OverTimeItem
{
	
	int timeLeft = 2;
	public void consume(DungeonCharacter entity) 
	{
		entity.modifyStrength(10);
		entity.addConsumableInUse(this);
	}

	
	public void consumeMessage() 
	{
		
		System.out.println("gained 10 strength!");
	}

	
	public boolean isConsumable() 
	{
		return true;
	}

	
	public String getName() 
	{
		return "Strength Potion";
	}

	
	public void undo(DungeonCharacter entity) 
	{
		
		entity.modifyStrength(-10);
		entity.removeConsumableInUse(this);
		System.out.println(entity.getName() + "'s Strength Potion wore off!");
	}

	
	public boolean reduceTime() 
	{
		timeLeft--;
		if(timeLeft == 0)
			return true;
		return false;
	}

}

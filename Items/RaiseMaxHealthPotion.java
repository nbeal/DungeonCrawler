package Items;

import DesignPatternsFinal.DungeonCharacter;

public class RaiseMaxHealthPotion implements OverTimeItem
{
	
	int timeLeft = 5;
	public void consume(DungeonCharacter entity) 
	{
		entity.addMaxHealth(20);
		entity.addConsumableInUse(this);
	}

	
	public void consumeMessage() 
	{
		
		System.out.println("gained 20 toward max health!");
	}

	
	public boolean isConsumable() 
	{
		return true;
	}

	
	public String getName() 
	{
		return "Raise Max Health Potion";
	}

	
	public void undo(DungeonCharacter entity) 
	{
		
		entity.addMaxHealth(-20);
		entity.removeConsumableInUse(this);
		System.out.println(entity.getName() + "'s Raise Max Health Potion wore off!");
	}

	
	public boolean reduceTime() 
	{
		timeLeft--;
		if(timeLeft == 0)
			return true;
		return false;
	}

}

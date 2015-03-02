
public class HealthPotion implements ConsumableItem
{

	String name = "Health Potion";
	public boolean isConsumable() 
	{
		return true;
	}


	public String getName() 
	{
		return name;
	}


	public void consume(DungeonCharacter entity) 
	{
		entity.heal(15);
	}


	public void consumeMessage() 
	{
		System.out.println("was healed for 15 hp!");
	}

}

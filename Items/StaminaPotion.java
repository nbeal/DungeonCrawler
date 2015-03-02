
public class StaminaPotion implements ConsumableItem
{

	@Override
	public boolean isConsumable() 
	{

		return true;
	}

	public String getName() 
	{
		
		return "Stamina Potion";
	}

	@Override
	public void consume(DungeonCharacter entity) 
	{
		entity.modifyStamina(-15);
		
	}

	@Override
	public void consumeMessage() 
	{
		System.out.println("gained 15 stamina!");
		
	}

}

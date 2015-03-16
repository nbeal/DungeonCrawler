package Items;

import DesignPatternsFinal.DamageHandler;
import DesignPatternsFinal.DungeonCharacter;

public class FirePotion implements ConsumableItem
{

	double totDamage = 1;
	@Override
	public boolean isConsumable() 
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() 
	{
		// TODO Auto-generated method stub
		return "Fire Potion";
	}

	@Override
	public void consume(DungeonCharacter entity) 
	{
		int[] damage = DamageHandler.fillArray("");
		damage[DamageHandler.DAMAGE_FIRE] = 10;
		totDamage = entity.modifyHealth(damage);
		
	}

	@Override
	public void consumeMessage() 
	{
		System.out.println("was hit for " + totDamage + " by a Fire Potion!");
		
	}

}

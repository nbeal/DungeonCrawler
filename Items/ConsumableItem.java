package Items;

import DesignPatternsFinal.DungeonCharacter;

public interface ConsumableItem extends Item
{
	public void consume(DungeonCharacter entity);
	public void consumeMessage();
}

package Items;

import DesignPatternsFinal.DungeonCharacter;

public interface OverTimeItem extends ConsumableItem
{
	public void undo(DungeonCharacter entity);
	public boolean reduceTime();

}

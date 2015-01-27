public class Battle
{
	public static void main(String[] args)
	{
		DungeonCharacter monster1 = new Blob();
		DungeonCharacter monster2 = new Blob();
		
		monster1.attack(monster2);
		System.out.println(monster2.getHealth());
	}
	
	

}
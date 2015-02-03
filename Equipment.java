public class Equipment
{
	private int hitPoints;
	private int strength;
	private int dexterity;
	private int stamina;
	private int defense;
	String name;
	String type;
	
	public Equipment()
	{
		this.hitPoints = 0;
		this.strength = 0;
		this.dexterity = 0;
		this.stamina = 0;
		this.defense = 0;
		this.name = "Nothing";
		this.type = "Nothing";
	}
	
	public Equipment(int hp, int str, int dex, int stam, int def, String tempName)
	{
		this.hitPoints = hp;
		this.strength = str;
		this.dexterity = dex;
		this.stamina = stam;
		this.defense = def;
		this.name = tempName;
	}
	
	public int getDef()
	{
		return defense;
	}
	
	public String getType()
	{
		return type;
	}

}
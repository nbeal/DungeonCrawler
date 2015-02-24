
public class DamageHandler
{
	private static DamageHandler instance = null;
	
	private static int[]	ARRAY_TEMPLATE;
	/** All of the damage types */
	public static int	DAMAGE_NORMAL = 0;
	public static int	DAMAGE_PIERCE = 1;
	public static int	DAMAGE_FIRE = 2;
	public static int	DAMAGE_WATER = 3;
	public static int	DAMAGE_ELEC = 4;
	
	protected DamageHandler()
	{
		      // Exists only to defeat instantiation.
			ARRAY_TEMPLATE = new int[5];
			ARRAY_TEMPLATE[0] = 0;
			ARRAY_TEMPLATE[1] = 0;
			ARRAY_TEMPLATE[2] = 0;
			ARRAY_TEMPLATE[3] = 0;
			ARRAY_TEMPLATE[4] = 0;
	}
	
	public static DamageHandler getInstance()
	{
		if(instance == null) 
		{
			instance = new DamageHandler();
		}
		return instance;
	}
	
	private static int[] getArray()
	{
		return ARRAY_TEMPLATE.clone();	
	}
	
	public static int[] fillArray(String Values)
	{
		String[] data = Values.split(",");
		int[] passedOut = getArray();
		for (int i = 0; i < data.length; i+=2)
		{
			passedOut[Integer.parseInt(data[i])] = Integer.parseInt(data[i + 1]);
		}
		return passedOut;
	}
	
	public static int[] modifyArray(int[] Array, String Values)
	{
		String[] data = Values.split(",");
		for (int i = 0; i < data.length; i+=2)
		{
			Array[Integer.parseInt(data[i])] = Integer.parseInt(data[i + 1]);
		}
		return Array;
	}
	public static int damageCalculation( int[] defender, int[] Damages)
	{
		int damageTaken = Math.max(0, Damages[DAMAGE_NORMAL] - defender[DAMAGE_NORMAL] * 1);
		damageTaken += Math.max(0, Damages[DAMAGE_PIERCE] - defender[DAMAGE_PIERCE] * 1);
		damageTaken += Math.max(0, Damages[DAMAGE_FIRE] - defender[DAMAGE_FIRE] * 1);
		damageTaken += Math.max(0, Damages[DAMAGE_WATER] - defender[DAMAGE_WATER] * 1);
		damageTaken += Math.max(0, Damages[DAMAGE_ELEC] - defender[DAMAGE_ELEC] * 1);
		return damageTaken;
	}
}

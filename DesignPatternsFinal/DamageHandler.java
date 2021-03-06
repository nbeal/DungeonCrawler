package DesignPatternsFinal;

public class DamageHandler
{
	private static DamageHandler instance = null;
	
	//number of 0's equals number of damage types
	private static int[]	ARRAY_TEMPLATE = new int[] {0,0,0,0,0,0,0,0};
	/** All of the damage types */
	public static int	DAMAGE_NORMAL = 0;
	public static int	DAMAGE_PIERCE = 1;
	public static int	DAMAGE_FIRE = 2;
	public static int	DAMAGE_WATER = 3;
	public static int	DAMAGE_ELEC = 4;
	public static int 	DAMAGE_HEAL = 5;
	public static int 	DAMAGE_COLD = 6;
	public static int 	DAMAGE_ACID = 7;
	
	private DamageHandler()
	{
		      // Exists only to defeat instantiation.
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
		if (!Values.equals(""))
		{
			for (int i = 0; i < data.length; i+=2)
			{
				passedOut[Integer.parseInt(data[i])] = Integer.parseInt(data[i + 1]);
			}
		}
		return passedOut;
	}
	
	public static int[] addToArray(int[] Array, String Values)
	{
		String[] data = Values.split(",");
		if (!Values.equals(""))
		{
			for (int i = 0; i < data.length; i+=2)
			{
				Array[Integer.parseInt(data[i])] = Array[Integer.parseInt(data[i])] + Integer.parseInt(data[i + 1]);
			}
		}
		return Array;
	}
	
	public static int[] removeValues(int[] Array, String Values)
	{
		String[] data = Values.split(",");
		if (!Values.equals(""))
		{
			for (int i = 0; i < data.length; i+=2)
			{
				Array[Integer.parseInt(data[i])] = Array[Integer.parseInt(data[i])] - Integer.parseInt(data[i + 1]);
			}
		}
		return Array;
	}
	
	public static double damageCalculation( int[] defender, int[] Damages)
	{
		double damageTaken = Math.max(0, Damages[DAMAGE_NORMAL] - defender[DAMAGE_NORMAL] * .5);
		damageTaken += Math.max(0, Damages[DAMAGE_PIERCE] - defender[DAMAGE_PIERCE] * .5);
		damageTaken += Math.max(0, Damages[DAMAGE_FIRE] - defender[DAMAGE_FIRE] * .5);
		damageTaken += Math.max(0, Damages[DAMAGE_WATER] - defender[DAMAGE_WATER] * .5);
		damageTaken += Math.max(0, Damages[DAMAGE_ELEC] - defender[DAMAGE_ELEC] * .5);
		damageTaken += Math.max(0, Damages[DAMAGE_COLD] - defender[DAMAGE_COLD] * .5);
		damageTaken += Math.max(0, Damages[DAMAGE_ACID] - defender[DAMAGE_ACID] * .5);
		damageTaken += Math.max(0, Damages[DAMAGE_HEAL] - defender[DAMAGE_HEAL] * .5);
		return damageTaken;
	}

	public static int[] addToArray(int[] Array, int[] attack)
	{
		for (int i = 0; i < Array.length; i++)
		{
			Array[i] = Array[i] + attack[i];
		}
		return Array;
	}
	
	public static int[] removeValues(int[] Array, int[] attack)
	{
		for (int i = 0; i < Array.length; i++)
		{
			Array[i] = Array[i] - attack[i];
		}
		return Array;
	}

	public static int getValue(String encoded)
	{
		switch (encoded)
		{
			case "DAMAGE_NORMAL":	return DAMAGE_NORMAL;
			case "DAMAGE_PIERCE":	return DAMAGE_PIERCE;
			case "DAMAGE_FIRE":		return DAMAGE_FIRE;
			case "DAMAGE_WATER":	return DAMAGE_WATER;
			case "DAMAGE_ELEC":		return DAMAGE_ELEC;
			case "DAMAGE_HEAL":		return DAMAGE_HEAL;
			case "DAMAGE_COLD":		return DAMAGE_COLD;
		}
		return Integer.parseInt(encoded);
		
	}
}

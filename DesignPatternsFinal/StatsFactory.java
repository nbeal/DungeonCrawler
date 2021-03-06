package DesignPatternsFinal;

import java.util.*;
import java.util.Map.Entry;

public class StatsFactory
{
	private Map<String, Stats> StatMap = new HashMap<String, Stats>();
	private static StatsFactory StatFact;
	
	private StatsFactory(){};
	
	public static StatsFactory getStatFact()
	{
		if(StatFact == null)
		{
			StatFact = new StatsFactory();
		}
		
		return StatFact;
	}
	public Stats getStats(String name) 
	{
		Stats stats = StatMap.get(name);
		if (stats == null) 
		{
			stats = new Stats(name);
			StatMap.put(name, stats);
		}
			return stats;
	}
	
	public int getTotalStatsMade() 
	{
		return StatMap.size();
	}
	
	public void levelUpAll(int hp, int str, int dex, int stam, int def)
	{
		for(Entry<String, Stats> entry : StatMap.entrySet()) 
		{
		    String key = entry.getKey();
		    Stats value = entry.getValue();

		    value.levelUp(hp, str, dex, stam, def);
		    
		    StatMap.put(key, value);
		}
	}
	
	
}
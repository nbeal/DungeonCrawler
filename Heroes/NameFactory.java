package Heroes;

import java.util.*;
import java.util.Map.Entry;

public class NameFactory
{

	private static NameFactory NameFact;
	private ArrayList<String> names = new ArrayList<String>();
	
	private NameFactory()
	{
		names.add("Conan");
		names.add("Gandalf");
		names.add("Frodo");
		names.add("Zeldor");
		names.add("Tyr");
		names.add("Bartimaeus");
		
		
	};
	
	public static NameFactory getNameFact()
	{
		if(NameFact == null)
		{
			NameFact = new NameFactory();
		}
		
		return NameFact;
	}
	
	public String getName() 
	{
		
		Random rnd = new Random();
		
		int index = rnd.nextInt(names.size());
		return names.remove(index);
	}
	
	
	
}
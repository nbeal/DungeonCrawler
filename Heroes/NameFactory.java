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
		names.add("Ted");
		names.add("Link");
		names.add("Iselidor");
		names.add("Tiny");
		names.add("Artemis");
		names.add("Leonidus");
        names.add("Rand");
        names.add("Zedd");
        names.add("Alistair");
        names.add("Morrigan");
        names.add("Sera");
        names.add("Carth");
        names.add("Kahlan");
        names.add("Link");
        names.add("Bartimaeus");
        names.add("Rahl");
        names.add("Eowen");
        names.add("Daknar");
        names.add("Dyana");
        names.add("Warren");
        names.add("Rorik");
		
		
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
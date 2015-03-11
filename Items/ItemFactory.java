package Items;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Equipment.Armor;
import Equipment.Equipment;
import Equipment.EquipmentFactory;
import Equipment.NullEquipment;
import Equipment.Weapon;

public class ItemFactory 
{
	private static final int totalItems = 10;
	private static ItemFactory instance = null;
	private static String fileName = "ItemList.xml";
	private static Document ItemFile;
	private static boolean valid = false;
	
	private ItemFactory()
	{
		      // Exists only to defeat instantiation.
	}
	
	public static ItemFactory getInstance()
	{
		if(instance == null) 
		{
			instance = new ItemFactory();
		}
		return instance;
	}
	
	public void readFile()
	{
		try
		{
	        File file = new File(fileName);
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        ItemFile = db.parse(file);
	        ItemFile.getDocumentElement().normalize();
	        if(!ItemFile.getDocumentElement().getNodeName().equals("items"))
	        {
	        	System.out.println("INVALID FILE");
	        	valid = false;
	        }
	        else
	        {
	        	valid = true;
	        }
		}
	    catch (Exception e) 
			{
                System.out.println("Cannot find Item XML!");
                System.exit(-1);
		    }
	}
		
	public Item loadItem()
	{
		Item loaded = null;
		if (valid)
		{
			
			try
			{
				int itemChoice = 1 + (int) (Math.random() * (totalItems - 1));
		        NodeList nodeLst = ItemFile.getElementsByTagName("possibleItems");
		        
		        Node fstNode = nodeLst.item(0);
		        Element fstElmnt = (Element) fstNode;
		        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("value" + itemChoice);
		        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
		        NodeList fstNm = fstNmElmnt.getChildNodes();
		        String itemName = ((Node) fstNm.item(0)).getNodeValue();
		        
		        if (itemName.equals("Equip"))
		        	loaded = EquipmentFactory.getInstance().loadEquipment();
		        
		        else
		        {
		        	itemName = "Items." + itemName;
		        	Class c = Class.forName(itemName);

		        	Object o = c.newInstance();

		        	loaded = (Item) o;
		        }
		     } 
		     catch (Exception e) 
			{
		        e.printStackTrace();
		     }
		}
		return loaded;
	}
}

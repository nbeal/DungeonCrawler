package Equipment;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EquipmentFactory
{
	private static final int totalEquipment = 26;
	private static EquipmentFactory instance = null;
	private static String fileName = "MyXMLFile.xml";
	private static Document equipmentFile;
	private static boolean valid = false;
	
	private EquipmentFactory()
	{
		      // Exists only to defeat instantiation.
	}
	
	public static EquipmentFactory getInstance()
	{
		if(instance == null) 
		{
			instance = new EquipmentFactory();
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
	        equipmentFile = db.parse(file);
	        equipmentFile.getDocumentElement().normalize();
	        if(!equipmentFile.getDocumentElement().getNodeName().equals("items"))
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
                System.out.println("Cannot find xml for equipment!");
                System.exit(-1);
		    }
	}
		
	public Equipment loadEquipment()
	{
		Equipment loaded = new NullEquipment();
		if (valid)
		{
			
			try
			{
				int equipment = 1 + (int) (Math.random() * (totalEquipment - 1));
		        NodeList nodeLst = equipmentFile.getElementsByTagName("equipment");
		        
		        Node fstNode = nodeLst.item(0);
		        Element fstElmnt = (Element) fstNode;
		        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("value" + equipment);
		        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
		        NodeList fstNm = fstNmElmnt.getChildNodes();
		        String equipDetail = ((Node) fstNm.item(0)).getNodeValue();
		        
		        String[] stats = equipDetail.split("~");
		        if (stats[0].equals("WEAPON"))
		        {
		        	loaded = new Weapon(stats);
		        }
		        else if (stats[0].equals("ARMOR"))
		        {
		        	loaded = new Armor(stats);
		        }
		     } 
		     catch (Exception e) 
			{
		        e.printStackTrace();
		     }
		}
		return loaded;
	}
	public Equipment loadEquipment(String target, int value)
	{
		Equipment loaded = new NullEquipment();
		if (valid)
		{
			
			try
			{
		        NodeList nodeLst = equipmentFile.getElementsByTagName(target);
		        
		        Node fstNode = nodeLst.item(0);
		        Element fstElmnt = (Element) fstNode;
		        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("value" + value);
		        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
		        NodeList fstNm = fstNmElmnt.getChildNodes();
		        String equipDetail = ((Node) fstNm.item(0)).getNodeValue();
		        
		        String[] stats = equipDetail.split("~");
		        if (stats[0].equals("WEAPON"))
		        {
		        	loaded = new Weapon(stats);
		        }
		        else if (stats[0].equals("ARMOR"))
		        {
		        	loaded = new Armor(stats);
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

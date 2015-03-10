package Equipment;

import Items.Item;
import Items.ItemFactory;

public class EquipmentTester {

	public static void main(String[] args)
	{
		//DamageHandler.getInstance();
		// TODO Auto-generated method stub
		Item test;
		ItemFactory tester = ItemFactory.getInstance();
		tester.readFile();
		test = tester.loadItem();
		test = tester.loadItem();
		test = tester.loadItem();
	}

}

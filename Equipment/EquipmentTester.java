package Equipment;

public class EquipmentTester {

	public static void main(String[] args)
	{
		//DamageHandler.getInstance();
		// TODO Auto-generated method stub
		Equipment test;
		EquipmentFactory tester = EquipmentFactory.getInstance();
		tester.readFile();
		test = tester.loadEquipment();
		test = tester.loadEquipment();
		test = tester.loadEquipment();
		test = tester.loadEquipment();
		test = tester.loadEquipment();
		test = tester.loadEquipment();
		test = tester.loadEquipment();
		test = tester.loadEquipment();
	}

}

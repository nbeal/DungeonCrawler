import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MazeGenerator 
{

	public static String[][] loadMap(String file) 
	{
		String line;
		String[][] protoMap;
		String dimensions = "";

		try 
		{
			Scanner in = new Scanner(new FileReader(file));

			dimensions = in.nextLine();

			protoMap = new String[dimensions.charAt(0) - 48][dimensions
					.charAt(2) - 48];

			int k = 0;

			while (in.hasNextLine()) 
			{
				line = in.nextLine();
				protoMap[k] = line.split("\\|");

				k++;
			}

			return protoMap;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		return null;
	}

	public static Room[][] convertMap(String[][] arra) 
	{
		Room[][] map = new Room[arra.length][arra[0].length];
		QuestionFactory fact = new QuestionFactory();
		//fact.genQlist();

		for (int k = 1; k < map.length - 1; k++) {
			for (int j = 1; j < map[0].length - 1; j++) 
			{

				if (arra[k][j].equals("r")) 
				{
					map[k][j] = new Room();

					if (arra[k - 1][j].equals("r"))
						map[k][j].setNorth(new Door(fact, k+1,j));

					if (arra[k][j + 1].equals("r"))
						map[k][j].setEast(new Door(fact, k,j+1));

					if (arra[k + 1][j].equals("r"))
						map[k][j].setSouth(new Door(fact, k+1,j));

					if (arra[k][j - 1].equals("r"))
						map[k][j].setWest(new Door(fact, k,j-1));
				}

			}
		}

		return map;
	}

	public static void printArray(String[][] arra)
	{
		for (int k = 0; k < arra.length; k++) {
			for (int j = 0; j < arra[k].length; j++) 
			{
				System.out.print(arra[k][j] + " ");
			}

			System.out.println("");
		}
	}

}

import java.io.*;
import java.util.*;

//Main Game Starter
public class Game
{
    private static Room[] rooms;
    private static Room current = new Room();
    private static boolean exit = false;
    private static Scan scan = new Scan();
    
    public static void initialize()
    {
        initialMessage();
        rooms = scan.scanInRooms();
        current = rooms[0];
    }
    
    public static void main(String[] args)
    {
        initialize();
        
        Scanner scanner = new Scanner(System.in);
        String input;
        
        while(!exit)
        {
            System.out.println("\nCurrent room id is " + current.getID() + ". " + current.getDescription());
            System.out.printf("\nWhich direction would you like to move to?\n> ");
            input = scanner.nextLine();
            handleCommand(input);
        }
        scanner.close();
    }
    
    private static void handleCommand(String userInput) {
		
		int direction;
		userInput = checkAbrev(userInput);
		switch(userInput.toLowerCase())
		{
			case "north":
				System.out.println("Going north...");
				direction = current.getNorth();
				moveRoom(direction);
			break;
			case "west":
				System.out.println("Going west...");
				direction = current.getWest();
				moveRoom(direction);
			break;
			case "south":
				System.out.println("Going south...");
				direction = current.getSouth();
				moveRoom(direction);
			break;
			case "east":
				System.out.println("Going east...");
				direction = current.getEast();
				moveRoom(direction);
			break;
			case "exit":
				System.out.println("Exiting...");
				exit = true;
			break;
			case "help":
				System.out.println("Possible Commands:");
				System.out.println("north\nwest\nsouth\neast\nexit\nhelp");
			break;
			
			default:
				System.out.println("Invalid Command");
			break;
		}
	}
	
	public static void moveRoom(int direction)
	{
	    if(direction == 0) //invalid direction
	    {
	        System.out.println("Invalid Direction");
	        return;
	    }
	    
	    //---------------------------------------------
	    
	    if(direction == 1) //edge case 1
	    {
    	    try{
    	        current = rooms[0]; //Moving room 
    	    }catch(Exception e)
    	    {
    	        System.out.println("You tried to walk through walls!");
    	    }
	    }
	    else
	    {
    	    try{
    	        current = rooms[direction]; //Moving room 
    	    }catch(Exception e)
    	    {
    	        System.out.println("You tried to walk through walls!");
    	    }
	    }
	    
	    //tryEncounter(current);  //Fight random encounter
	}
        
    private static void initialMessage()
    {
        System.out.println("You and your party have started on your great quest to rid the halls of the Dread Lord Crypt of evil. You entered the long sealed doors and when you stepped in, the doors slammed shut sealing you in with the evil within.");
    }
    
    private static String checkAbrev(String userInput)
    {
        if(userInput.equals("n"))
            return "north";
        if(userInput.equals("w"))
            return "west";
        if(userInput.equals("s"))
            return "south";
        if(userInput.equals("e"))
            return "east";
        return userInput;
    }
    
}


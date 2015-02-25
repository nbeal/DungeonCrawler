import java.io.*;
import java.util.*;

//Main Game Starter
public class Game
{
    private static Room[] rooms;
    private static Menu _menu;
    private static Room _current;
    private static boolean exit;
    private static Scan scan = new Scan();
    
    public static void initialize()
    {
        initialMessage();
        
        scan = new Scan();
        rooms = scan.scanInRooms();
        _menu = Menu.getInstance();
        _current = new Room(); _current = rooms[0];
        exit = false;
    }
    
    public static void main(String[] args)
    {
        initialize();
        String input, userInput;
        
        Scanner scanner = new Scanner(System.in);
        while(!exit)
        {
            System.out.println("\nCurrent room id is " + _current.getID() + ". " + _current.getDescription());
            System.out.printf("\nWhich direction would you like to move to?\n> ");
            input = scanner.nextLine();
            userInput = _menu.handleCommand(input);
            checkCommand(userInput);
            
        }
        scanner.close();
    }
	
	public static void checkCommand(String userInput)
	{
		if(userInput == null)
			return;
		if(userInput.equals("exit"))
		{
			exit = true;
			return;
		}
		moveRoom(_current.getDirection(userInput));
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
    	        _current = rooms[0]; //Moving room 
    	    }catch(Exception e)
    	    {
    	        System.out.println("You tried to walk through walls!");
    	    }
	    }
	    else
	    {
    	    try{
    	        _current = rooms[direction]; //Moving room 
    	    }catch(Exception e)
    	    {
    	        System.out.println("You tried to walk through walls!");
    	    }
	    }
	    
	    //tryEncounter(current);
	}
        
    private static void initialMessage()
    {
        System.out.println("You and your party have started on your great quest to rid the halls of the Dread Lord Crypt of evil. You entered the long sealed doors and when you stepped in, the doors slammed shut sealing you in with the evil within.");
    }
    

    
}


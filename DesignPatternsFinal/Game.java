package DesignPatternsFinal;

import java.io.*;
import java.util.*;

//Main Game Starter
public class Game
{
    private static int 		_encounterChance = 80; //chance of encounter in unchecked room
    private static int 		_clearedChance = 5;    //chance of encounter in checked room
	
    private static int 		_keys;
    private static Room[] 	_rooms;
    private static Menu 	_menu;
    private static Room 	_current;
    private static boolean 	_exit;
    private static Scan 	_scan;
    private static String   _previous;
    
    public static void initialize()
    {
        initialMessage();
        
        _scan 	 = new Scan();
        _rooms 	 = _scan.scanInRooms();
        _menu 	 = Menu.getInstance();
        _current = new Room(); _current = _rooms[0];
        _exit 	 = false;
        _keys	 = 0;
        _previous= "south";
    }
    
    public static void main(String[] args)
    {
        initialize();
        String input, userInput;
        
        Scanner scanner = new Scanner(System.in);
        while(!_exit)
        {
            System.out.printf("\nCurrent room id is " + _current.getID() + ". " + _current.getDescription() + " You came from the " + _previous + ". ");
            if(_current.getKey() != 0) { System.out.printf("There appears to be a shiny key in the corner."); }
            System.out.printf("\n\nWhich direction would you like to move to?\n> ");
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
			_exit = true;
			return;
		}
		if(userInput.equals("get"))
		{
			if(_current.getKey() != 0)
			{
				System.out.println("\nPicking up key...");
				_keys++;
				return;
			}
			System.out.println("\nNothing to pick up...");
			return;
		}
		moveRoom(_current.getDirection(userInput), userInput);
	}
	
	public static void moveRoom(int direction, String userInput)
	{
	    if(direction == 0) //invalid direction
	    {
	        System.out.println("\nYOu cannot go in that direction!");
	        return;
	    }
	    //---------------------------------------------
	    //check for door lock
		if(checkLock(direction))
			return;
	    //---------------------------------------------
	    //edge case Room 1
	    /*
	    if(direction == 1) 
	    {
    	    try{
    	    	_current.setCleared(true);
    	        _current = _rooms[0]; //Moving room 
    	    }catch(Exception e)
    	    {
    	        System.out.println("You tried to walk through walls!");
    	    }
	    }*/
	    //---------------------------------------------
	    //else
	    //{
    	    try{
    	    	_current.setCleared(true);
    	        _current = _rooms[direction-1]; //Moving room 
    	        getPrevious(userInput);
    	    }catch(Exception e)
    	    {
    	        System.out.println("You tried to walk through walls!");
    	    }
	    //}
	    
	    tryEncounter();
	}
    
    private static void tryEncounter()
    {
    	Random rand = new Random();
    	int randNum = rand.nextInt(100) + 1;
    	
    	//have not cleared room yet
    	if(!_current.getCleared())
    	{
	    	if(randNum < _encounterChance) 
	    	{
	    		//start Battle
    		}
    	}
    	
    	//have already cleared
    	else 
    	{
    		if(randNum < _clearedChance) 
	    	{
	    		//start Battle
    		}
    	}
    }   
    
    private static boolean checkLock(int direction)
    {
    	if(_current.getLocked() == direction) 
	    {
	    	if(direction == 12)
	    	{
	    		if(_keys > 0)
	    		{
	    			System.out.println("\nYou unlock the door...");
	    			_current.setLocked("0");
	    			return false;
	    		}
	    		else
	    		{
	    			System.out.println("\nThis door has a lock. You need a key!");
	    			return true;
	    		}
	    	}//end 12
	    	
	    	else if(direction == 25)
	    	{
	    		if(_keys > 1)
	    		{
	    			System.out.println("\nYou unlock the multiple locks on the door...");
	    			_current.setLocked("0");
	    			return false;
	    		}
	    		else
	    		{
	    			System.out.println("\nThis door has more than one lock. You need another key!");
	    			return true;
	    		}
	    	}//end 25
	    }
	    return false;
    }

    public static void getPrevious(String userInput)
    {
        if(userInput.equals("north"))
            _previous =  "south";
        if(userInput.equals("south"))
            _previous =  "north";
        if(userInput.equals("east"))
            _previous =  "west";
        if(userInput.equals("west"))
            _previous = "east";
    }

    
    private static void initialMessage()
    {
    	//http://www.network-science.de/ascii/
	/*	System.out.println("     _________          _______     ______   _______  _______  _______  ______      _        _______  _______  ______ ");
		System.out.println("     \__   __/|\     /|(  ____ \   (  __  \ (  ____ )(  ____ \(  ___  )(  __  \\   ( \      (  ___  )(  ____ )(  __  \ ");
		System.out.println("        ) (   | )   ( || (    \/   | (  \  )| (    )|| (    \/| (   ) || (  \  )   | (      | (   ) || (    )|| (  \  )");
		System.out.println("        | |   | (___) || (__       | |   ) || (____)|| (__    | (___) || |   ) |   | |      | |   | || (____)|| |   ) |");
    	System.out.println("        | |   |  ___  ||  __)      | |   | ||     __)|  __)   |  ___  || |   | |   | |      | |   | ||     __)| |   | |");
    	System.out.println("        | |   | (   ) || (         | |   ) || (\ (   | (      | (   ) || |   ) |   | |      | |   | || (\ (   | |   ) |");
    	System.out.println("        | |   | )   ( || (____/\   | (__/  )| ) \ \__| (____/\| )   ( || (__/  )   | (____/\| (___) || ) \ \__| (__/  )");
    	System.out.println("        )_(   |/     \|(_______/   (______/ |/   \__/(_______/|/     \|(______/    (_______/(_______)|/   \__/(______/");
    	System.out.println();
    	System.out.println("				  				 _______  _______           _______ _________");
    	System.out.println("				  				(  ____ \(  ____ )|\     /|(  ____ )\__   __/");
    	System.out.println("								| (    \/| (    )|( \   / )| (    )|   ) ( ");
    	System.out.println("								| |      | (____)| \ (_) / | (____)|   | |");
    	System.out.println("								| |      |     __)  \   /  |  _____)   | |");
    	System.out.println("								| |      | (\ (      ) (   | (         | |");
    	System.out.println("								| (____/\| ) \ \__   | |   | )         | |");
    	System.out.println("								(_______/|/   \__/   \_/   |/          )_( ");
    	System.out.println();*/
    	
        System.out.println("You and your party have started on your great quest to rid the halls of the Dread Lord Crypt of evil. You entered the long sealed doors and when you stepped in, the doors slammed shut sealing you in with the evil within.");
    }
    
}

package DesignPatternsFinal;
//Singleton Menu

public class Menu
{
    private static Menu _menu;
    
    private Menu() {}
    
    public static Menu getInstance()
    {
        if(_menu != null)
            return _menu;
        else
        {
            _menu = new Menu();
            return _menu;
        }
    }//end getInstance

    public static String handleCommand(String userInput) {

		userInput = checkAbrev(userInput);
		switch(userInput.toLowerCase())
		{
			case "north":
				System.out.println("\nGoing north...");
				return userInput;
			
			case "west":
				System.out.println("\nGoing west...");
				return userInput;
			
			case "south":
				System.out.println("\nGoing south...");
				return userInput;
			
			case "east":
				System.out.println("\nGoing east...");
				return userInput;

            case "unlock door":
                userInput = "unlock";

            case "unlock":
                return userInput;

            case "status":
                return userInput;

            case "equipstatus":
            	return userInput;
            		
            case "description":
                userInput = "room";
            case "room":
                return userInput;

			case "take":
			    userInput = "get";
			case "get":
			    return userInput;

            case "inv":
			case "stuff":
                userInput = "inventory";                
            case "inventory":
                return userInput;

			case "level up":
				userInput = "level";
				
			case "level":
				return userInput;
			    
            case "exit":
				return userInput;
			
			case "help":
				System.out.println("\nPossible Commands:");
				System.out.println("north/west/south/east\nstatus\nroom/description\ninventory/inv/stuff\nunlock\nget/pick up/take\nlevel/level up\nEquipStatus\nexit\nhelp");

				return null;
				
			default:
				System.out.println("\nInvalid Command!");
                System.out.println("Type help for a list of commands.");
				return null;
		}
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
        
        if(userInput.contains("key"))
            return "get";
            
        return userInput;
    }

}//end Menu class

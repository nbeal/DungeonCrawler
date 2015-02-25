
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
		
		int direction;
		userInput = checkAbrev(userInput);
		switch(userInput.toLowerCase())
		{
			case "north":
				System.out.println("Going north...");
				return userInput;
			
			case "west":
				System.out.println("Going west...");
				return userInput;
			
			case "south":
				System.out.println("Going south...");
				return userInput;
			
			case "east":
				System.out.println("Going east...");
				return userInput;
			
			case "exit":
				System.out.println("Exiting...");
				return userInput;
			
			case "help":
				System.out.println("Possible Commands:");
				System.out.println("north\nwest\nsouth\neast\nexit\nhelp");
				return null;
			
			default:
				System.out.println("Invalid Command");
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
        return userInput;
    }

}//end Menu class

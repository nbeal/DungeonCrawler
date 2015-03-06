package DesignPatternsFinal;

import java.util.*;

import Heroes.Hero;
import Items.FirePotion;
import Items.HealthPotion;
import Items.StaminaPotion;
import Items.StrengthPotion;
import Heroes.HeroFactory;

//Main Game Starter
public class Game
{
    private static int 		_encounterChance = 100; //chance of encounter in unchecked room
    private static int 		_clearedChance = 5;    //chance of encounter in checked room

    private static int 		_keys;
    private static Room[] 	_rooms;
    private static Menu 	_menu;
    private static Room 	_current;
    private static boolean 	_exit;
    private static boolean  _seen;
    private static Scan 	_scan;
    private static String   _previous;
    private static Inventory inventory = Inventory.getInventory();
    private static DungeonCharacter[] heroes = new Hero[4];

    public static void initialize()
    {
        initialMessage();

        _scan 	 = new Scan();
        _rooms 	 = _scan.scanInRooms();
        _menu 	 = Menu.getInstance();
        _current = new Room(); _current = _rooms[0];
        _exit 	 = false;
        _seen    = true;
        _keys	 = 0;
        _previous= "south";

        inventory.addItem(new HealthPotion());
        inventory.addItem(new StaminaPotion());
        inventory.addItem(new FirePotion());
    }

    public static void main(String[] args)
    {
        initialize();
        String input, userInput;

        Scanner scanner = new Scanner(System.in);

        selectHeroes(scanner);
        System.out.println();

        while(!_exit)
        {
            if(_seen)
            {
                System.out.printf(/*"\nCurrent room id is " + _current.getID() + ". " +*/ _current.getDescription() + " You came from the " + _previous + ". ");
                if(_current.getKey() != 0) { System.out.printf("There appears to be a shiny key in the corner."); }
                _seen = false;
            }
            System.out.printf("\n\nWhat would you like to do?\n> ");
            input = scanner.nextLine();
            userInput = _menu.handleCommand(input);
            checkCommand(userInput);
        }
        scanner.close();
    }

    public static void selectHeroes(Scanner input)
    {
        HeroFactory factory = new HeroFactory();
        String[] possibleHeroes = factory.getTypes();

        System.out.println("Choose your party:\n");

        for(int k = 0; k < 4; k++)
        {
            int choice = 0;
            for(int j = 1; j <= possibleHeroes.length; j++)
            {
                System.out.println(j + ") " + possibleHeroes[j-1]);
            }

            System.out.printf("\n>");

            while(choice < 1 || choice > possibleHeroes.length)
            {
                try {
                    choice = input.nextInt();
                    if(choice < 1 || choice > possibleHeroes.length) {
                        System.out.printf("\nInvalid choice! Please try again\n>");
                        input.nextLine();
                    }
                }catch(InputMismatchException e)
                {
                    input.nextLine();
                    System.out.printf("\nInvalid choice! Please try again\n>");
                    while(choice < 1 && choice > possibleHeroes.length)
                        choice = input.nextInt();
                }
            }
            input.nextLine(); //clears buffer
            heroes[k] = factory.order(possibleHeroes[choice - 1]);
        }
    }

    public static void checkCommand(String userInput)
    {
        if(userInput == null)
            return;
        else if(userInput.equals("exit"))
        {
            _exit = true;
            return;
        }
        else if(userInput.equals("status"))  //ADD STATUS HP/STAMINA OF PARTY
        {
            System.out.println("\nYou have " + _keys + " key(s).");
            CharacterPrint.getInstance().StatusPrint(heroes, "Party");
            return;
        }
        else if(userInput.equals("level"))
        {
        	//System.out.println("Who would you like to level up?");
        	int choice = CharacterPrint.getInstance().singleCharacterSelect(heroes);
        	heroes[choice].spendPoint();
        	return;
        }
        else if(userInput.equals("get"))
        {
            if(_current.getKey() != 0)
            {
                System.out.println("\nPicking up key...");
                _current.setKey("0");
                _keys++;
                return;
            }
            System.out.println("\nNothing to pick up...");
            return;
        }
        else if(userInput.equals("unlock"))
        {
            tryUnlock();
            return;
        }

        moveRoom(_current.getDirection(userInput), userInput);
    }

    public static void moveRoom(int direction, String userInput)
    {
        if(direction == 0) //invalid direction
        {
            System.out.println("\nYou cannot go in that direction!");
            return;
        }
        //---------------------------------------------
        //check for door lock
        if(checkLock(direction))
            return;
        //---------------------------------------------

        try{
            _current.setCleared(true);
            _current = _rooms[direction-1]; //Moving room
            getPrevious(userInput);
            _seen = true;
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

        //Boss Battle
        if(_current.getID().equals("25"))
        {
            return;
        }

        //have not cleared room yet
        if(!_current.getCleared())
        {
            if(randNum < _encounterChance)
            {
                Battle battle = new Battle(heroes, inventory);
                battle.printDescription();
                battle.startBattle();
            }
        }

        //have already cleared
        else
        {
            if(randNum < _clearedChance) //uncomment when done debugging
            {
                //Battle battle = new Battle(heroes, inventory);
                //battle.printDescription();
                //battle.startBattle();
            }
        }
    }

    private static boolean checkLock(int direction)
    {
        if(_current.getLocked() == direction)
        {
            System.out.println("\nThis door has a lock. You need a key!");
            return true;
        }
        return false;
    }

    private static void tryUnlock()
    {
        if(_keys == 0) {
            System.out.println("\nYou need a key to do this action!");
            return;
        }
        if(_current.getID().equals("11") && _current.getLocked() != 0)
        {
            System.out.println("\nYou unlock the door...");
            _current.setLocked("0");
            return;
        }

        if(_current.getID().equals("23") && _current.getLocked() != 0 && _keys == 1)
        {
            System.out.println("\nYou notice multiple locks on the door. You need another key.");
            return;
        }
        if(_current.getID().equals("23") && _current.getLocked() != 0 && _keys > 1)
        {
            System.out.println("\nYou unlock the multiple locks on the door...");
            _current.setLocked("0");
            return;
        }
        if(_keys > 0) {
            System.out.println("\nThere is nothing to unlock...");
            return;
        }
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
		System.out.println("     _________          _______     ______   _______  _______  _______  ______      _        _______  _______  ______ ");
		System.out.println("     \\__   __/|\\     /|(  ____ \\   (  __  \\ (  ____ )(  ____ \\(  ___  )(  __  \\    ( \\      (  ___  )(  ____ )(  __  \\ ");
		System.out.println("        ) (   | )   ( || (    \\/   | (  \\  )| (    )|| (    \\/| (   ) || (  \\  )   | (      | (   ) || (    )|| (  \\  )");
		System.out.println("        | |   | (___) || (__       | |   ) || (____)|| (__    | (___) || |   ) |   | |      | |   | || (____)|| |   ) |");
    	System.out.println("        | |   |  ___  ||  __)      | |   | ||     __)|  __)   |  ___  || |   | |   | |      | |   | ||     __)| |   | |");
    	System.out.println("        | |   | (   ) || (         | |   ) || (\\ (   | (      | (   ) || |   ) |   | |      | |   | || (\\ (   | |   ) |");
    	System.out.println("        | |   | )   ( || (____/\\   | (__/  )| ) \\ \\__| (____/\\| )   ( || (__/  )   | (____/\\| (___) || ) \\ \\__| (__/  )");
    	System.out.println("        )_(   |/     \\|(_______/   (______/ |/   \\__/(_______/|/     \\|(______/    (_______/(_______)|/   \\__/(______/");
    	System.out.println();
    	System.out.println("			  	  				   _______  _______           _______ _________");
    	System.out.println("			  	  				  (  ____ \\(  ____ )|\\     /|(  ____ )\\__   __/");
    	System.out.println("			  					  | (    \\/| (    )|( \\   / )| (    )|   ) ( ");
    	System.out.println("			  					  | |      | (____)| \\ (_) / | (____)|   | |");
    	System.out.println("								  | |      |     __)  \\   /  |  _____)   | |");
    	System.out.println("								  | |      | (\\ (      ) (   | (         | |");
    	System.out.println("								  | (____/\\| ) \\ \\__   | |   | )         | |");
    	System.out.println("								  (_______/|/   \\__/   \\_/   |/          )_( ");
    	System.out.println();

        System.out.println("You and your party have started on your great quest to rid the halls of the Dread Lord Crypt of evil. You entered the long sealed doors and when you stepped in, the doors slammed shut sealing you in with the evil within.");
    }

}

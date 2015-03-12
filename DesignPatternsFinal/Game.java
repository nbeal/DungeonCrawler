package DesignPatternsFinal;

import java.util.*;

import Equipment.EquipmentFactory;
import Heroes.Hero;
import Items.FirePotion;
import Items.HealthPotion;
import Items.Item;
import Items.ItemFactory;
import Items.StaminaPotion;
import Heroes.HeroFactory;

//Main Game Starter
public class Game
{
    private static int 		_encounterChance = 80; //chance of encounter in unchecked room
    private static int 		_clearedChance = 5;    //chance of encounter in checked room

    private static int 		_keys;
    private static int 		_won;
    private static Room[] 	_rooms;
    private static Menu 	_menu;
    private static Room 	_current;
    private static boolean  _playAgain;
    private static boolean 	_exit;
    private static boolean  _seen;
    private static boolean	_alive;
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
        EquipmentFactory.getInstance().readFile();
        ItemFactory.getInstance().readFile();
        _current = new Room(); _current = _rooms[0];
        _exit 	 = false;
        _seen    = true;
        _alive	 = true;
        _keys	 = 0;
        _won     = 0;
        _previous= "south";

        inventory.addItem(new HealthPotion());
        inventory.addItem(new StaminaPotion());
        inventory.addItem(new FirePotion());
    }

    public static void runGame()
    {
        Scanner scanner = new Scanner(System.in);
        _playAgain = true;
        while(_playAgain) {
            initialize();
            String input, userInput;

            selectHeroes(scanner);
            System.out.println();

            while (!_exit && _alive && _won == 0) {
                if (_seen) {
                    System.out.printf(/*"\nCurrent room id is " + _current.getID() + ". " +*/ _current.getDescription() + " You came from the " + _previous + ". ");
                    if (_current.getKey() != 0) {
                        System.out.printf("There appears to be a shiny key in the corner.");
                    }
                    _seen = false;
                }
                System.out.printf("\n\nWhat would you like to do?\n> ");
                input = scanner.nextLine();
                userInput = _menu.handleCommand(input);
                checkCommand(userInput);
            }
            if (_won != 1)
            {
                System.out.println("Exiting...");
                _playAgain = false;
            }
            else
            {
                playAgain(scanner);
            }
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
            System.out.println("Party Member " + (k+1) + "/4");
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
        else if(userInput.equals("status"))
        {
            System.out.println("\nYou have " + inventory.getKeys() + " key(s).");
            CharacterPrint.getInstance().StatusPrint(heroes, "Party");
            return;
        }
        else if(userInput.equals("equipstatus"))
        {
        	for (DungeonCharacter hero: heroes)
        	{
        		System.out.println(hero.getName());
        		hero.printEquipment();
        		System.out.println();
        	}
        	return;
        }
        else if(userInput.equals("room"))
        {
            System.out.printf( _current.getDescription() + " You came from the " + _previous + ". ");
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
                inventory.addKey();
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
        else if(userInput.equals("inventory"))
        {
        	inventory.useItems(heroes);
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
            Battle battle = new Battle(heroes, inventory, 1);
            _won = battle.startBossBattle();
            return;
        }

        //have not cleared room yet
        if(!_current.getCleared())
        {
            if(randNum < _encounterChance)
            {
                Battle battle = new Battle(heroes, inventory);
                battle.printDescription();
                _alive = battle.startBattle();
                if(_alive)
                	findItem();
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

    private static void findItem() 
    {
		// TODO Auto-generated method stub
    	System.out.println();
    	Item[] droppedItems = new Item[4];
    	int drop = 100;
    	int number = 0;
    	double dropped = Math.random() * 100;
    	while (drop > dropped)
    	{
    		drop -= 30;
    		dropped = Math.random() * 100;
    		//droppedItems[number] = EquipmentFactory.getInstance().loadEquipment();
    		droppedItems[number] = ItemFactory.getInstance().loadItem();
    		number++;
    	}
    	System.out.println("You found the following items: ");
		for (int i = 0; i< number; i++)
		{
			System.out.println(droppedItems[i].getName());
			inventory.addItem(droppedItems[i]);
		}
		System.out.println("");
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
    	StatsFactory statFact = StatsFactory.getStatFact();
        if(inventory.getKeys() == 0) {
            System.out.println("\nYou need a key to do this action!");
            return;
        }
        if(_current.getID().equals("11") && _current.getLocked() != 0)
        {
            System.out.println("\nYou unlock the door...");
            statFact.levelUpAll(10, 5, 5, 10, 5);
            _current.setLocked("0");
            return;
        }

        if(_current.getID().equals("17") && _current.getLocked() != 0 && inventory.getKeys() == 1)
        {
            System.out.println("\nYou notice two locks on the door. You need another key.");
            return;
        }
        if(_current.getID().equals("17") && _current.getLocked() != 0 && inventory.getKeys() > 1)
        {
            System.out.println("\nYou unlock the door...");
            statFact.levelUpAll(10, 5, 5, 10, 5);
            _current.setLocked("0");
            return;
        }


        if(_current.getID().equals("23") && _current.getLocked() != 0 && inventory.getKeys() <= 2)
        {
            System.out.println("\nYou notice three locks on the door. You need another key.");
            return;
        }
        if(_current.getID().equals("23") && _current.getLocked() != 0 && inventory.getKeys() > 2)
        {
            System.out.println("\nYou unlock the multiple locks on the door...");
            statFact.levelUpAll(10, 5, 5, 10, 5);
            _current.setLocked("0");
            return;
        }
        if(inventory.getKeys() > 0) {
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
        System.out.println("						--Copyright © 2015 Kevin Murray, Nicholas Valentine, & Nathan Beal--\n");

        System.out.println("You and your party have started on your great quest to rid the halls of the Dread Lord Crypt of evil. You entered the long sealed doors and when you stepped in, the doors slammed shut sealing you in with the evil within.");
    }

    public static void playAgain(Scanner scanner) {
        System.out.printf("\nWould you like to play again? (y/n)\n>");
        String input = scanner.nextLine();

        if(input.toLowerCase().equals("y"))
            return;
        _playAgain = false;
    }
}

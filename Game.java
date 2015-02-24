import java.io.Scanner;
import java.util.*;

//Main Game Starter
public class Game
{
    
    private Room[] rooms = new Room[25];
    
    public Game ()
    {
        
    }
    
    public void initialize()
    {
        initialMessage();
        scanInRooms();
        
    }
    
    private static String[][] scanInRooms()
    {
        //0.ID-1.NAME-2.DESC-3.EXIT(NWSE)-4.ITEM
        int numAttrib = 5; //See above
        
        String[] rawData = new String[25];
        String[] parsedData = new String[25];
        String[][] arrayRooms = new String[25][5]; //[Room][attrib]
        String delims = "[-]";
        
        Scanner roomScan;
        try{
            roomScan = new Scanner(new File("Rooms.txt"));
            
                    int i = 0;
        
            while(roomScan.hasNextLine())
            {
                rawData[i] = roomScan.nextLine();      //Line of text
                parsedData = rawData[i].split(delims); //split on -
                for(int z = 0; z < numAttrib; z++)
                    arrayRooms[i][z] = parsedData[z];  //passed into 2D array
                i++;
                
            }//end while
        }catch(FileNotFoundException e)
        {
            //Terminate Program as there is no map
        }
                
        return arrayRooms;
    }
        /*tester
        public static void main(String[] args)
        {
            String[][] arrayRooms = scanInRooms();
            
            for(int x = 0; x < 11; x++)
            {
                System.out.println("Room " + x + ": ");
                for(int i = 0; i < 5; i++)
                    System.out.println(arrayRooms[x][i])
            }
        
        }*/
        
    private void initialMessage()
    {
        System.out.println("You and your party have started on your great quest to rid the halls of the Dread Lord Crypt of evil. You entered the long sealed doors and when you stepped in, the doors slammed shut sealing you in with the evil within.");
    }
    
    
}

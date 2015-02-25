import java.io.*;
import java.util.*;

public class Scan
{
    
    public static Room[] scanInRooms()
    {
        //0.ID-1.NAME-2.DESC-3.EXIT(NWSE)-4.Key-5.LockedDoor
        int numAttrib = 6; //See above
        int numRooms = 25;
        
        Room[] rooms = new Room[25];
        String[] rawData = new String[numRooms];
        String[] parsedData = new String[numRooms];
        String[][] arrayRooms = new String[numRooms][numAttrib]; //[Room][attrib]
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
            System.out.println("No map file!");
            System.exit(-1);
        }
        
        //set room details
        for(int x = 0; x < numRooms; x++)
        {
            Room room = new Room();
            room.setID(arrayRooms[x][0]);
            room.setName(arrayRooms[x][1]);
            room.setDescription(arrayRooms[x][2]);
            room.setExits(arrayRooms[x][3]);
            room.setKey(arrayRooms[x][4]);
            room.setLocked(arrayRooms[x][5]);
            rooms[x] = room; //added room to array of rooms
        }
        return rooms;
    }

}

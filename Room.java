import java.io.*;
import java.util.*;

public class Room
{
    private String name, description, id;
    private String[] directions = new String[5];
    private int north, west, south, east, key, lock;
    private boolean cleared;
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String passed)
    {
        this.name = passed;
    }

    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String passed)
    { 
        this.description = passed;
    }
    
    public String getID()
    {
        return this.id;
    }
    
    public void setID(String passed)
    {
        this.id = passed;
    }
    
    public void setExits(String passed)
    {
        //System.out.println(passed); //debug
        String delims = "[,]";
        String[] parsed = passed.split(delims);
        directions = parsed;
        
        try{
            north = Integer.parseInt(parsed[0]);
            west  = Integer.parseInt(parsed[1]);
            south = Integer.parseInt(parsed[2]);
            east  = Integer.parseInt(parsed[3]);
        }catch(Exception e)
        {
            System.out.println("SetExits() broke!");
        }
    }
    
    public int getDirection(String userInput)
    {
        switch(userInput.toLowerCase())
		{
			case "north":
				return getNorth();
			case "west":
				return getWest();
			case "south":
				return getSouth();
			case "east":
				return getEast();
			default:
				System.out.println("Invalid Command");
			break;
		}
		return -1;
    }
    
    public int getNorth() {return this.north;}
    public int getWest()  {return this.west;}
    public int getSouth() {return this.south;}
    public int getEast()  {return this.east;}
    
    public void setKey(String passed)
    {
        this.key = Integer.parseInt(passed);
    }
    
    public int getKey()
    {
        return this.key;
    }
    
    public boolean getCleared()
    {
        return this.cleared;
    }
    
    public void setCleared(boolean thing)
    {
        this.cleared = thing;
    }
    
    public int getLocked()
    {
        return this.lock;
    }
    
    public void setLocked(String locked)
    {
        this.lock = Integer.parseInt(locked);
    }
}

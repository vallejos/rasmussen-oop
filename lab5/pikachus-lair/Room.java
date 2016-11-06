/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

import java.util.HashMap;
import java.util.Set;

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Monster pokemon;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        pokemon = null;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param up The up exit.
     * @param down The down exit.
     */
    public void setExits(String direction, Room room) 
    {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    /**
    * Return a description of the room’s exits,
    * for example, "Exits: north west".
    * @return A description of the available exits.
    */
   public String getExitString() {
        String result = "Available Paths: ";
        Set<String> keys = exits.keySet();

        for (String exit : keys) {
           result += " " + exit;
        }

        return result;
   }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
    * Return a long description of this room, of the form:
    *     You are in the kitchen.
    *     Exits: north west
    * @return A description of the room, including exits.
    */
   public String getLongDescription()
   {
       return "You are " + description + ".\n" + getExitString();
   }

   /**
    * Adds a pokemon to the room
    */
    public void addMonster(Monster monster) {
        pokemon = monster;
    }
    
    /**
     * Removes a pokemon from the rooms
     */
    public void removeMonster() {
        pokemon = null;
    }    
    
    /**
     * Returns pokemon in the room
     */
    public Monster getMonster() {
        return pokemon;
    }
    
    /**
     * Checks if the room has monsters or not.
     */
    public boolean hasMonsters() {
        if (pokemon != null) {
            return true;
        }
        return false;
    }
    
}


/**
 * Write a description of class Monster here.
 * 
 * @author (Leonardo Vallejos) 
 * @version (a version number or a date)
 */

import java.util.HashMap;
import java.util.Set;
import java.util.Random;

public class Char
{
    private String name; // character name
    private HashMap<String, Monster> collection; 
    private int inventory; // only pokeballs in this version so this is just an int
    private int level;

    /**
     * Constructor for objects of class Char
     */
    public Char()
    {
        name = "";
        collection = new HashMap<>();
        inventory = 10; // start with 20 pokeballs
        level = 1; // start in level 1
    }
    
    /**
     * Returns player name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets player name
     */
    public void setName(String charName)
    {
        name = charName;
    }

    /**
     * Returns pokeballs
     */
    public int getInventory()
    {
        return inventory;
    }

    /**
     * Adds a pokemon to the collection
     */
    public void addMonster(Monster monster) {
        collection.put(monster.getName(), monster);
    }
    
    /**
     * Print a list of captured pokemons
     */
    public void printCollection()
    {
        System.out.print("Captured Pokemons: ");
        Set<String> keys = collection.keySet();
        
        if (keys.size() > 0) {
            for (String key : keys) {
                Monster pokemon = collection.get(key);
                System.out.print(pokemon.getName() + " ");
            }
        } else {
            System.out.println("0");
        }
        System.out.println();
    }

    /**
     * Print items in inventory
     */
    public void printInfo() {
        System.out.print("Items in inventory: ");
        System.out.println(getInventory() + " pokeballs");
        printCollection();
    }

    /**
     * Check if there are enough pokeballs available in the inventory
     */
    public boolean canCatch() {
        if (inventory > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Attempt to capture the pokemon throwing a ball
     */
    public boolean throwBall(Monster pokemon) {
        inventory--;

        // chance that the attack will be successfull
        int chance = 100 - pokemon.getLevel();
        Random rand = new Random();
        int a = rand.nextInt(100) * level;
        
        System.out.println(a + " < " + chance + "?");

        if (a < chance) {
            // player wins the battle => capture the pokemon
            addMonster(pokemon);
            inventory += pokemon.getPokeballs();
            return true;
        }
        
        // pokemon wins the battle
        return false;
    }
    
    /**
     * Checks if the player has captured the boss
     */
    public boolean hasPikachu() {
        Monster pikachu = collection.get("Pikachu");
        if (pikachu != null) {
            return true;
        }
        return false;
    }
    
}

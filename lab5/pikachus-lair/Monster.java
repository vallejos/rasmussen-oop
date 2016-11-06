
/**
 * Write a description of class Monster here.
 * 
 * @author (Leonardo Vallejos) 
 * @version (a version number or a date)
 */
public class Monster
{
    private String pokemonName; // pokemon name
    private int pokeballs; // the number of pokeballs that will drop
    private int difficultyLevel; // used to calculate the success ratio of the pokemon (common, rare, epic, boss)
    private String levelName;
    
    /**
     * Constructor for objects of class Monster
     */
    public Monster(String name, int loot, String level)
    {
        pokemonName = name; // pokemon name
        pokeballs = loot; // how many pokeballs to drop
        levelName = level; // set pokemon level
        difficultyLevel = calculateDifficulty();
    }

    private int calculateDifficulty() {
        switch (levelName) {
            case "rare":
                return 60;
            case "epic":
                return 70;
            case "boss":
                return 80;
        }
        
        // common
        return 35;
    }
    
    
    /**
     * Returns name
     */
    public String getName()
    {
        return pokemonName;
    }

    /**
     * Returns pokeballs
     */
    public int getPokeballs()
    {
        return pokeballs;
    }

    /**
     * Returns pokemon level
     */
    public int getLevel()
    {
        return difficultyLevel;
    }

    
}

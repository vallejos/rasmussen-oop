/**
 *  This class is the main class of the "Pikachu's Lair" application. 
 *  Based on "World of Zuul" text based adventure game and on "Pokemon Go".  
 *  Users can walk around some scenery trying to catch the ellusive Pikachu,
 *  a boss level pokemon. Be carefull, as there are many other Pokemons around.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Leonardo Vallejos
 * @version 2016.11.05
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Char player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        createPlayer();
        parser = new Parser();
    }


    /**
     * Creates a char for the player.
     */
    private void createPlayer() {
        player = new Char();
        player.setName("Trainer");
    }
    
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // rooms based on map locations: 0, 1, 12, 2, 3, 6, 13, 14, 15, 10, 9, 11, 8, 7, 5, 4
        Room outside, entrance, entranceNorth, entranceSouth, southSmallChamber,
                southBigChamber, northLongEnd, northShortEnd, northCross, northEastCorner,
                lairEntrance, lair, eastEnd, southEnd, middleEnd, middleCross;
      
        // create the rooms
        outside = new Room("outside. There's a mountain nearby and you can see an entrance to the east"); // 0
        entrance = new Room("inside an enigmatic cavern. You can hear a misterious voice whispering in the dark..."); // 1
        entranceSouth = new Room("in a narrow passage with almost no light"); // 2
        southSmallChamber = new Room("in a small and dark chamber. The path ends ahead."); // 3
        middleCross = new Room("in a silent tunnel. You can see a small tunnel nearby that goes deeper down the mountain"); // 4
        middleEnd = new Room("in a small dead end"); // 5
        southBigChamber = new Room("in a big deep chamber, wet, hot, and dark"); // 6
        southEnd = new Room("in a dead end"); // 7
        eastEnd = new Room("in a dead end. It's hot here and nowhere else to move forward."); // 8
        lairEntrance = new Room("alone in a wet tunnel, surrounded by darkness, but you can hear something breathing..."); // 9
        northEastCorner = new Room("in a path in a corner"); // 10
        lair = new Room("deep inside the cave, in a dark chamber. You can hear bones crushing in the floor as you step in"); // 11
        entranceNorth = new Room("in a quiet tunnel with almost no light"); // 12
        northLongEnd = new Room("in a dead end tunnel"); // 13
        northShortEnd = new Room("in a short end tunnel"); // 14
        northCross = new Room("in a complete tunnel"); // 15

        // initialise room exits: north, east, south, west, up, down
        outside.setExits("entrance", entrance);
        entrance.setExits("north", entranceNorth);
        entrance.setExits("south", entranceSouth);
        entrance.setExits("outside", outside);
        entranceSouth.setExits("north", entrance);
        entranceSouth.setExits("east", middleCross);
        entranceSouth.setExits("south", southSmallChamber);
        southSmallChamber.setExits("north", entranceSouth);
        middleCross.setExits("north", middleEnd);
        middleCross.setExits("west", entranceSouth);
        middleCross.setExits("down", southBigChamber);
        middleEnd.setExits("south", middleCross);
        southBigChamber.setExits("north", lairEntrance);
        southBigChamber.setExits("east", eastEnd);
        southBigChamber.setExits("south", southEnd);
        southBigChamber.setExits("up", middleCross);
        southEnd.setExits("north", southBigChamber);
        eastEnd.setExits("west", southBigChamber);
        lairEntrance.setExits("north", northEastCorner);
        lairEntrance.setExits("south", southBigChamber);
        lairEntrance.setExits("down", lair);
        northEastCorner.setExits("south", lairEntrance);
        northEastCorner.setExits("up", northCross);
        lair.setExits("up", lairEntrance);
        entranceNorth.setExits("north", northLongEnd);
        entranceNorth.setExits("east", northCross);
        entranceNorth.setExits("south", entrance);
        northLongEnd.setExits("south", entranceNorth);
        northShortEnd.setExits("south", northCross);
        northCross.setExits("north", northShortEnd);
        northCross.setExits("west", entranceNorth);
        northCross.setExits("down", northEastCorner);

        // create some pokemons
        Monster haunter, snorlax, umbreon, entei, gyarados, dragonite, arcanine, mew, 
            charizard, abbra, pikachu;
        
        haunter = new Monster("Haunter", 5, "common");
        snorlax = new Monster("Snorlax", 10, "rare");
        umbreon = new Monster("Umbreon", 3, "common");
        entei = new Monster("Entei", 2, "common");
        gyarados = new Monster("Gyarados", 5, "common");
        dragonite = new Monster("Dragonite", 12, "rare");
        arcanine = new Monster("Arcanine", 11, "rare");
        mew = new Monster("Mew", 9, "rare");
        charizard = new Monster("Charizard", 20, "epic");
        abbra = new Monster("Abbra", 19, "epic");
        pikachu = new Monster("Pikachu", 30, "boss");

        // put those pokemons in the rooms
        middleEnd.addMonster(haunter);
        lairEntrance.addMonster(snorlax);
        middleCross.addMonster(umbreon);
        entranceSouth.addMonster(entei);
        entranceNorth.addMonster(gyarados);
        southBigChamber.addMonster(dragonite);
        northEastCorner.addMonster(arcanine);
        northLongEnd.addMonster(mew);
        northCross.addMonster(charizard);
        southSmallChamber.addMonster(abbra);
        lair.addMonster(pikachu); // boss here :)
        
        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (player.getInventory() == 0 && !player.hasPikachu()) {
                System.out.println("-> Unfortunatelly, you have failed to capture Pikachu and you don't have enough pokeballs to continue!!");
                System.out.println("-> Game Over!!");
                finished = true;
            } else if (player.hasPikachu()) {
                System.out.println("-> Well done!! You have defeated the boss and Pikachu is now in your collection!");
                finished = true;
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Pikachu's Lair " + player.getName() + "!");
        System.out.println("Venture into the cavern and defeat Pikachu.");
        System.out.println("Become a master pokemon trainer and collect all different Pokemons.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("collection")) {
            showCollection();
        }
        else if (commandWord.equals("inventory")) {
            showInventory();
        }
        else if (commandWord.equals("catch")) {
            if (currentRoom.hasMonsters()) {
                fight();
            } else {
                System.out.println("There's nothing here to catch");
            }
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    
    private void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
    }
    
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are alone searching for Pikachu, an elussive and lethal electric pokemon.");
        System.out.println("Be alert at all times, there are dangers around.");
        System.out.println("Use the 'look' command to search for pokemons in the different locations.");
        System.out.println("Use the 'catch' command to throw a pokeball to a pokemon and try to catch it.");
        System.out.println("You gotta catch'em all and become a master trainer. Good Luck!");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("You cannot pass!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    private void look()
    {
       System.out.println("You are " + currentRoom.getDescription());
       searchForMonsters();
       System.out.println(currentRoom.getExitString());
    }

    private void fight() {
        Monster pokemon = currentRoom.getMonster();

        if (player.canCatch()) {
            System.out.println();
            System.out.println("You carefully throw a pokeball to " + pokemon.getName() + "...");

            if (capture(pokemon)) {
                System.out.println("-> SUCCESS: You captured " + pokemon.getName() + "! Well done!!");
                System.out.println("-> Loot: " + pokemon.getPokeballs() + " pokeballs added to your inventory.");
                System.out.println();
                currentRoom.removeMonster();
            } else {
                System.out.println("-> FAIL: Your pokeball misses " + pokemon.getName());
                System.out.println();
            }
        } else {
            System.out.println("You don't have enough pokeballs to capture this pokemon.");
        }

        showInventory();        
    }
    
    private boolean capture(Monster pokemon) {
        return player.throwBall(pokemon);
    }
    
    private void showCollection() {
        player.printCollection();
    }

    private void showInventory() {
        player.printInfo();
    }
    
    private void searchForMonsters() {
        if (currentRoom.hasMonsters()) {
            Monster pokemon = currentRoom.getMonster();
            System.out.println("-> A wild " + pokemon.getName() + " appears!!");
        } else {
            System.out.println("You sense a presence, but there's nothing else here. Keep searching!");
        }
        
    }
}

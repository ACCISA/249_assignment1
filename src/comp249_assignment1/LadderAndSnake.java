package comp249_assignment1;

import java.util.Scanner;

/**
 * This class represents the Ladder and Snake game.
 * Creating an instance of this class will create the game. All necessary methods for the game functionalities are called within this class.
 * 
 *
 * 
 * 
 *
 */
public class LadderAndSnake {
	/**
	 * int nbPlayers, the amount of players playing (currently limited to 2)
	 */
	private int nbPlayers; 
	/**
     * Player[] player, the player array of player objects. The length and the contents of this array depends on nbPlayers

	 */
	private Player[] players;
	public static int nbTurns;
	/**
	 * determines if the game automatically plays out or if it is manually played 

	 */
	public static boolean isManual;
	/**
	 * Constructor for the LadderAndSnake class.
	 * 
	 * Currently, the number of players is limited to 2.
	 * 
	 * @param nbPlayers, numbers of players playing.
	 */
	public LadderAndSnake(int nbPlayers) {
		
		if (nbPlayers >= 2) {
			if(nbPlayers == 2) {
				this.nbPlayers = nbPlayers;
				players = new Player[2];

				return;
			}
			System.out.println("Warning! Currently, you can only play with 2 players.");

			nbPlayers = 2; // OVERRIDE PLAYER INPUT
			this.nbPlayers = 2;
			players = new Player[2];
			return;
		}
		System.out.println("You can only play with two players!");
		System.exit(0);
	}
	
	
	/**
	 * Default constructor for the LadderAndSnake that will set the nbPlayers to 2
	 * 
	 * 
	 * 
	 */
	public LadderAndSnake() {
		this.nbPlayers = 2;
		players = new Player[2];
	}

	/**
	 * 
	 * Sets order of who starts by rolling the dice and checking who rolled a greater number.
	 * If both players roll the same dice it increments the number of attempts and goes back in the loop.
	 * Prints the name of who starts and breaks the while loop.
	 * 
	 * 
	 * 
	 */
    private void setPlayOrder() {
        int attempts=0;
        do{
        	attempts++;
       
        	int[] diceRolls = new int[2];
        	for (int i = 0; i < nbPlayers; i++) {
        		int roll = flipDice();
        		diceRolls[i] = roll;
        	}
        	boolean player1Bigger = (diceRolls[0]>diceRolls[1])? true : false;
        	boolean isSameValue = (diceRolls[0] == diceRolls[1])? true : false;
        	if (isSameValue) {
        		for (int i = 0; i < nbPlayers; i++) {
            		System.out.println(players[i].getName() + " rolled " + diceRolls[i]);
        		}
        		System.out.println("A tie achieved between " + players[0].getName() + " and " + players[1].getName() + ". Attempting to break the tie.");
        		continue;
        	}
        	if (player1Bigger) {
        		for (int i = 0; i < nbPlayers; i++) {
            		System.out.println(players[i].getName() + " rolled " + diceRolls[i]);
        		}
        		System.out.println("Reached final decision on order of playing: " + players[0].getName() + " then " + players[1].getName() + ". It took " + attempts + " attempts before a decision could be made.");
        		break;
        	}
        	for (int i = 0; i < nbPlayers; i++) {
        		System.out.println(players[i].getName() + " rolled " + diceRolls[i]);
    		}
    		System.out.println("Reached final decision on order of playing: " + players[1].getName() + " then " + players[0].getName() + ". It took " + attempts + " attempts before a decision could be made.");
    		reversePlayers();
    		break;
        } while(true);
        
    }
	
	/**
	 * this method returns a random integer value between 1 and 6.
	 * 
	 * @return int between 1 and 6
	 */
	private int flipDice() {
		final int min = 1;
		final int max = 6;
		final int range = max - min + 1;
		return (int)(Math.random() * range) + min;
	}
	
	/**
	 * Reverses the players position
	 * 
	 */
	private void reversePlayers() {
		Player temp = players[0];
		players[0] = players[1];
		players[1] = temp;
	}
	
	/**
	 *  This method uses the Scanner to wait for the player to type 'f' to continue playing.
	 *  
	 *  If the static variable isManual is set to false this method will be ignored
	 *  
	 *  
	 *  @param p, current player that is rolling the dice
	 * 
	 */
	private void waitForInput(Player p) {
		if (!(LadderAndSnake.isManual)) return;
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("It is " + p.getName() + "'s turn. Press 'f'.");
			if (!(input.next().equals("f"))) continue;
			break;
			
		}
	}
	
	/**
	 * This method will start the ladder and snake game.It creates a board object, creates the players and sets their order. 
	 * 
	 * At every player's dice roll it checks for any events depending on their current position (e.g. snakes, ladders or other players) and it updates their position and board location.
	 * The board is printed after each turn. The game will only end when a player reaches the board position 100.
	 * 
	 */

	public void play() {
		Events event = new Events(); // populate the events hashmap

		Board boardOut = new Board(); // create the board
		for (int i = 0; i < nbPlayers; i++) { // create the player objects depending on the amount of players playing
			Player.nbPlayersCreated++;
			Player player = new Player();
			players[i] = player;
		}
		Player.allPlayers = players;
		System.out.println("Players created");
		setPlayOrder();
	  
		System.out.println("------");
		while (!(Player.isGameDone)) { // this actually plays the game.
			for (int i = 0; i < nbPlayers; i++) { // for every player, wait for input if necesasry then roll the dice, than update the position, then print the board
				waitForInput(players[i]);
				int flip = flipDice();
				System.out.println(players[i].getName() + " rolled " + flip);
				LadderAndSnake.nbTurns++;
				players[i].updatePosition(flip);
				boardOut.SetPlayers(players[i]);
				boardOut.Show();
			}
		}
	}

	/**
	 * getter that returns the integer number of players
	 * 
	 * @return numbers of players playing the game
	 */
	public int getNbPlayers() {
		return nbPlayers;
	}
	


	
}
	
	

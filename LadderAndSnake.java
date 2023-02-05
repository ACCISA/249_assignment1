package comp249_assignement1;

import java.util.Scanner;

/**
 * 
 * Attributes:
 * array called players to track the players information.
 * boolean called isManual will be used to ask users to enter 'f' to flip dice. 
 *
 */
public class LadderAndSnake {

	private int nbPlayers;
	private Player[] players;
	public static int nbTurns;
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
			System.out.println("Warning! Currently, you can only play with 2 players.");
			nbPlayers = 2; // OVERRIDE PLAYER INPUT
			this.nbPlayers = 2;
			players = new Player[2];
			return;
		}
		System.exit(0);
	}
	
	public LadderAndSnake() {
		this.nbPlayers = 2;
		players = new Player[2];
	}

	/*
	 * This method does not return anything.
	 * Sets order of who starts by rolling the dice and checking who rolled a greater number.
	 * If both players roll the same dice it increments the number of attempts and goes back in the loop.
	 * Prints the name of who starts and breaks the while loop.
	 * Order is initially set to Player 1 and then Player 2. If Player 2 is greater then reversePlayer method is called.
	 * 
	 */
    private void setPlayOrder() {
        int attempts=0;
        do{
        	attempts++;
        	int player1 = flipDice();
        	int player2 = flipDice();
        	boolean player1Bigger = (player1>player2)? true : false;
        	boolean isSameValue = (player1==player2)? true : false;
        	if (isSameValue) {
        		System.out.println(players[0].getName() + " rolled " + player1);
        		System.out.println(players[1].getName() + " rolled " + player2);
        		System.out.println("A tie achieved between " + players[0].getName() + " and " + players[1].getName() + ". Attempting to break the tie.");
        		continue;
        	}
        	if (player1Bigger) {
        		System.out.println(players[0].getName() + " rolled " + player1);
        		System.out.println(players[1].getName() + " rolled " + player2);
        		System.out.println("Reached final decision on order of playing: " + players[0].getName() + " then " + players[1].getName() + ". It took " + attempts + " attempts before a decision could be made.");
        		break;
        	}
        	System.out.println(players[0].getName() + " rolled " + player1);
    		System.out.println(players[1].getName() + " rolled " + player2);
    		System.out.println("Reached final decision on order of playing: " + players[1].getName() + " then " + players[0].getName() + ". It took " + attempts + " attempts before a decision could be made.");
    		reversePlayers();
    		break;
        } while(true);
        
    }
	
	/*
	 * this method returns a random integer value between 1 and 6.
	 */
	private int flipDice() {
		final int min = 1;
		final int max = 6;
		final int range = max - min + 1;
		return (int)(Math.random() * range) + min;
	}
	
	/*
	 * this method reverses the players position and is void
	 */
	private void reversePlayers() {
		Player temp = players[0];
		players[0] = players[1];
		players[1] = temp;
	}
	
	/*
	 *  This method takes in a Player object to get the player's name and to print a message asking for user input.
	 *  If boolean called IsManual is true this method will print the message asking for user input.
	 *  If IsManual is set to false this method will ignore. 
	 */
	private void waitForInput(Player p) {
		if (!(LadderAndSnake.isManual)) return;
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("It is " + p.getName() + "'s turn. Press 'f'.");
			if (!(input.next().equals("f"))) continue;
			break;
			
		}
		input.close();
	}
	
	// plays the game
	/*
	 * This method is void. It creates a board object, creates the players and sets their order. 
	 * After setting the order of turns it alternates between the players and starts the game.
	 * At every player's turn it checks for any events(e.g. snakes or ladders) and it updates their position and location.
	 * It prints the board after each turn.
	 */
	public void play() {
		Board boardOut = new Board();
		for (int i = 0; i < nbPlayers; i++) {
			Player.nbPlayersCreated++;
			Player player = new Player();
			players[i] = player;
		}
		Player.allPlayers = players;
		System.out.println("Players created");
		setPlayOrder();
	  
		System.out.println("------");
		while (!(Player.isGameDone)) {
			for (int i = 0; i < nbPlayers; i++) {
				waitForInput(players[i]);
				int flip = flipDice();
				System.out.println(players[i].getName() + " rolled " + flip);
				LadderAndSnake.nbTurns++;
				players[i].updatePosition(flip);
				boardOut.SetLocations(players[i]);
				boardOut.Show();
			}
		}
	}

	/*
	 * getter that returns the integer number of players
	 */
	public int getNbPlayers() {
		return nbPlayers;
	}
	
	/*
	 * getter that returns the location of the array of players
	 */
	public Player[] getPlayers() {
		return players;
	}
	
	
	
}
	
	

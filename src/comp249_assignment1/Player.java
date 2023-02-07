package comp249_assignment1;

import java.util.Scanner;


/**
 * This class represents the player information needed/used in the snakes and ladder game it.
 * This class extends the LadderAndSnake class.
 *
 */
public class Player extends LadderAndSnake{
	/**
	 * String name, used to assign each player a name.
	 * 
	 */
	private String name;
	/**
	 * int position, position of the player.
	 */
	private int position;
	/**
	 * int nbPlayersCreated, number of players that are created. We are limiting the total number of players to 2 for this assignment.
	 */
	public static int nbPlayersCreated;
	/**
	 * boolean isGameDone, isGameDone is set to false as long as the game is not finished.
	 */
	public static boolean isGameDone = false;
	/**
	 * array allPlayers, static array of players to get all the players in the game. This variable is used in the other classes.
	 */
	public static Player[] allPlayers;
	
	/**
	 * This method asks for the user to input their player name.
	 * Returns a String indicating the name of the player entered by the user.
	 * @return
	 */
	private String writeName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please Insert a name Player " + nbPlayersCreated );
		while(true) {
			String name = input.next();
			if (name.length() < 3) {
				System.out.println("Please insert a name with at least 3 characters");
				continue;
			}
			return name;			
		}
	}
	
	/**
	 * 
	 * It checks for any possible event and then updates the position of the player according to that.
	 * @param position
	 * @return void
	 */
	public void updatePosition(int position) {
		int positionExpect = this.position + position;
		Events a = new Events();
		int eventLocation = a.checkForEvent(positionExpect,this);
		if (eventLocation == -1) {
			return;
		}
		this.position = eventLocation;

	}
	
	/**
	 * Default constructor that sets the position to 0 and calls the writeName method to set the name.
	 * This prints a message indicating that a player was created along with the player's name.
	 * 
	 */
	public Player() {
		this.position = 0;
		this.name = writeName();
		System.out.println("You have created a Player! Name: " + this.name);
	}
	/**
	 *  equals method that returns true if the calling object and the passed object is the same.
	 *  @return boolean
	 */
	public boolean equals(Object player) {
		if (player == null) return false;
		if (!(player.getClass().equals(this.getClass()))) return false;
		if (!(player == this)) return false;
		return true;
	}
	
	/**
	 * getter that returns the name of the Player object.
	 * @return name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setter that takes in a String parameter and assigns it to the name attribute of the calling object.
	 * @param name
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getter that returns the position of the calling object.
	 * @return int position.
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * setter that takes in an integer value and assigns it to the variable position.
	 * @param position
	 * @return void
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	
	
	
}

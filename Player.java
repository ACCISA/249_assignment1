package comp249_assignement1;

import java.util.Scanner;

public class Player extends LadderAndSnake{
	private String name;
	private int position;
	public static int nbPlayersCreated;
	public static boolean isGameDone = false;
	public static Player[] allPlayers;
	
	/*
	 * This method asks for user input.
	 * Returns a String indicating the name of the player entered by the user.
	 */
	private String writeName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please Insert a name Player " + nbPlayersCreated );
		return input.next();
		
	}
	
	/*
	 * This method is void. 
	 * It checks for any possible event and then updates the position of the player according to that.
	 */
	public void updatePosition(int position) {
		int positionExpect = this.position + position;
		Events a = new Events();
		int eventLocation = a.checkForEvent(positionExpect,this,false);
		if (eventLocation == -1) {
			return;
		}
		this.position = eventLocation;

	}
	
	/*
	 * Default constructor that sets the position to 0 and calls the writeName method to set the name.
	 * This prints a message indicating that a player was created along with the player's name.
	 */
	public Player() {
		this.position = 0;
		this.name = writeName();
		System.out.println("You have created a Player! Name: " + this.name);
	}
	/*
	 * equals method that returns true if the calling object and the passed object has the same class, the same content and is not null.
	 */
	public boolean equals(Object player) {
		if (player == null) return false;
		if (!(player.getClass().equals(this.getClass()))) return false;
		if (!(player == this)) return false;
		return true;
	}
	
	/*
	 * getter that returns the name of the Player object.
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * setter that takes in a String parameter and assigns it to the name attribute of the calling object.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * getter that returns the position of the calling object.
	 */
	public int getPosition() {
		return position;
	}
	
	/*
	 * setter that takes in an integer value and assigns it to the variable position.
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	
	
	
}

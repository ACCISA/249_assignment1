package lab1_comp249;

import java.util.Scanner;

public class Player extends LadderAndSnake{
	private String name;
	private int position;
	public static int nbPlayersCreated;
	public static boolean isGameDone = false;
	public static Player[] allPlayers;
	private String writeName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please Insert a name Player " + nbPlayersCreated );
		return input.next();
	}
	
	
	
	public void updatePosition(int position) {
		int positionExpect = this.position + position;
		Events a = new Events();
		int eventLocation = a.checkForEvent(positionExpect,this,false);
		this.position = eventLocation;

	}
	
	public Player() {
		this.position = 0;
		this.name = writeName();
		System.out.println("You have created a Player! Name: " + this.name);
	}
	
	public boolean equals(Object player) {
		if (player == null) return false;
		if (!(player.getClass().equals(this))) return false;
		if (!(player == this)) return false;
		return true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	
	
	
}

package comp249_assignment1;

import java.util.HashMap;
import java.util.Scanner;


/**
 * This class represents the events of the snake and ladders game. It extends the LadderAndSnake class.
 * 
 *
 */
public class Events extends LadderAndSnake{
	
	/**
	 * HashMap where the keys represent the start of the ladder or the start of the snake and the value represent the end of the ladder or the end of the snake. 
	 */
	public final static HashMap<String,String> events = new HashMap<String, String>();
	
	/**
	 * 	Default constructor for the Events class. It assigns the keys the position of the start of a ladder or snake and assigns the value the position that the ladders and snake end at.
	 */
	public Events() {
		// Ladders
		events.put("1", "38");
		events.put("4", "14");
		events.put("9", "31");
		events.put("28", "84");
		events.put("21", "42");
		events.put("36", "44");
		events.put("51", "67");
		events.put("71", "91");
		events.put("80", "100");

		// Snakes
		events.put("16", "6");
		events.put("48", "30");
		events.put("64", "60");
		events.put("79", "19");
		events.put("93", "68");
		events.put("95", "24");
		events.put("97", "76");
		events.put("98", "78");
		
		
	}
	/**
	 * This method checks if there is a snake or ladder at the location passed in the parameter.
	 * @param location, the location the player is trying to go to.
	 * @return true if the new location is bigger than the old location.
	 */
	public static boolean isLadder(int location) {
		int returnLocation = Integer.parseInt(events.get(location+""));
		if (location > returnLocation) return false;
		return true;
	}
	/**
	 * This method checks for any events and updates the location according to that.
	 * It checks if the player landed on 100, went over 100, landed on a snake or ladder, and if the player landed on the location of another player. 
	 * @param location, the player is trying to move to.
	 * @param player, player that you are checking the event for.
	 * @return the int location that the player ends up in.
	 */
	public int checkForEvent(int location, Player player) {

		if (location > 100) { // player went over 100 go back
			int locationBackward = 100 - (location - 100);
			location = locationBackward;
		}
		
		if (location == 100) { // check if u win
			System.out.println("The Game is over. " + player.getName() + " has won!");
			System.exit(0);
		}
		if (LadderAndSnake.nbTurns != 1) { // to avoid NullPointerException when only 1 turn is done
			for (int i = 0; i < Player.allPlayers.length; i++) { // check if u land on another players location
				if (player.equals(Player.allPlayers[i])) {
					continue;
				} else {
					System.out.println(Player.allPlayers[i].getName() + " is currently at tile #" + Player.allPlayers[i].getPosition());
				}
				if (player.getName().equals(Player.allPlayers[i].getName())) continue;
				if (Player.allPlayers[i].getPosition() == location) {
					System.out.println("Pos: " + location);
					Player.allPlayers[i].setPosition(0);//send him back to 0
					return location;
				}
			}
		}
		
		String check = events.get(location+"");
		if ((check == null || (check+"").length() == 0 || (check+"").equals("null"))) {
			System.out.println(player.getName()+" is currently at tile #" + location ); // nothing was found, there are no events
			return location;
		}
		
		int locationToGo = Integer.parseInt(events.get(location+""));

		if (isLadder(location)) {
			System.out.println(player.getName() + " landed on a ladder. Go to tile #"+locationToGo);
			if (locationToGo == 100) {// hit the ladder that makes you win
				System.out.println("The Game is over. " + player.getName() + " has won!");
				System.exit(0);
			}
			return locationToGo;
		}
		System.out.println(player.getName() + " landed on a ssssnake. Go to tile #"+locationToGo);
		return locationToGo;
	}
}

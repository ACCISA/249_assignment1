package comp249_assignement1;

import java.util.HashMap;
import java.util.Scanner;

public class Events extends LadderAndSnake{
	public final static HashMap<String,String> events = new HashMap<String, String>();
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
	
	public static boolean isLadder(int location) {
		int returnLocation = Integer.parseInt(events.get(location+""));
		if (location > returnLocation) return false;
		return true;
	}

	public int checkForEvent(int location, Player player, boolean recursive) {

		if (location > 100) { // player went over 100 go back
			int locationBackward = 100 - (location - 100);
			System.out.println("YOU WENT OVER GO BACK TO " + locationBackward + "---------------------------------------------");
			location = locationBackward;
		}
		
		if (location == 100) { // check if u win
			System.out.println("The Game is over. " + player.getName() + " has won!");
			System.exit(0);
		}
		if (LadderAndSnake.nbTurns != 1) { // to avoid NullPointerException when only 1 turn is done
			for (int i = 0; i < Player.allPlayers.length; i++) { // check if u land on another players location
				if (player.equals(Player.allPlayers[i])) {
					System.out.println(player.getName()+"; loc: " + location);
				} else {
					System.out.println(Player.allPlayers[i].getName() + "; loc: " + Player.allPlayers[i].getPosition());
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
		if ((check == null || (check+"").length() == 0 || (check+"").equals("null")) && !(recursive)) {
			System.out.println(player.getName()+" moved to: " + location ); // nothing was found, there are no events
			return location;
		}
		if ((check == null || (check+"").length() == 0 || (check+"").equals("null")) && recursive) {
			System.out.println(player.getName() + " went over, go back to: " + location);
			return location;
			
		} // nothing was found, there are no events
		
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

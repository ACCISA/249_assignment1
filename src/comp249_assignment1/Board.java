package comp249_assignment1;

import java.util.ArrayList;
/**
 * 
 * This class represents the board of the snake and ladders game.
 *
 */
public class Board {
	/**
	 * An attribute of the Board class. 
	 * ArrayList containing the players.
	 */
	private ArrayList<Player> plys = new ArrayList<>();
	/**
	 *  2D array String[][] board, visual representation of the board before any event has occurred. This board is updated as the game is played.
	 */
	private String[][] board = new String[][] {
		{"L8","","S5","S6","","S7","","S8","","L9"},
		{"","","","L5","","","","","",""},
		{"L8","S3","S5","","S6","","","","","L9"},
		{"","","","S4","","","L7","S8","",""},
		{"S4","","","","","","","","","L7"},
		{"","L4","","L6","","","","S2","",""},
		{"","","L1","","L6","","","","","L3"},
		{"L4","","","S7","","","","L5","","S2"},
		{"","S3","","","S1","","L2","","",""},
		{"L1","","","L2","","S1","","","L3",""},
	
	};
	/**
	 * takes the location as a parameter, finds its corresponding row and column on the board.
	 * @return the array of the row and column index.
	 */
	private int[] LocationToIndex(int loc) { // TODO
		int row = (loc -1)/10;
		int col = (loc -1)%10;
		row = 9 - row; // image the position on the board
		if (loc > 10) {
			if (row % 2 != 0)return new int[] {row,col}; // odd rows ignore
			col = 9 - col;
		}
		return new int[] {row,col};
	}
	/**
	 * It adds the players to the array list of players.
	 * @param ply, players to add to the player list.
	 * @return void
	 */
	public void SetPlayers(Player ply) {
		for (int i = 0; i < plys.size(); i++) { // go trough the amount of players and assign them to the plys array
			if (plys.get(i).equals(ply)) {
				plys.remove(i);
				plys.add(ply);
				return;
			}
		}
		plys.add(ply);
		System.out.println("player added");
	}
	/**
	 * This method prints the board and visually represents where the players, ladders and the snakes are. 
	 * @return void
	 */
	public void Show() {
		System.out.println("===================================================");
		int[][] locations = new int[plys.size()][2];
		for (int i = 0; i < plys.size(); i++) { // prepare the players location so we can check for them when we print the board
			int[] boardLocation = LocationToIndex(plys.get(i).getPosition());
			locations[i] = boardLocation;
		}
		for (int i = 0; i < board.length; i++) { // print the 2d array by going from row to row
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) { // go through every column
				boolean isPlayer = false;
				for (int k = 0; k < plys.size(); k++){ // go to the players and look if the board location we are about to print is where a player is located
				//	smt++;
					if (i == locations[k][0] && j == locations[k][1]) {
						System.out.print(plys.get(k).getName().substring(0,2)+" | ");
						isPlayer = true;
						continue;
					}
				}
				if (isPlayer) continue;
				if (board[i][j].equals("")) {
					System.out.print("__ | ");
					continue;
				}
				System.out.print(board[i][j]+" | ");
				
			}
//			System.out.println("--------------------------------");
			System.out.println();
			System.out.println("___________________________________________________");

		}
		System.out.println("===================================================");

	}
	
	
}

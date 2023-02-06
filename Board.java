package comp249_assignement1;

import java.util.ArrayList;

public class Board {
	
	private ArrayList<Player> plys = new ArrayList<>();
	
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
	/*
	 * takes the location as a parameter, finds its corresponding row and column on the board and returns the array at those indices.
	 */
	private int[] LocationToIndex(int loc) { // TODO
		int row = (loc -1)/10;
		int col = (loc -1)%10;//9
		row = 9 - row; // image the position on the board
		if (loc > 10) {
			if (row % 2 != 0)return new int[] {row,col}; // odd rows ignore
			col = 9 - col;
		}
		return new int[] {row,col};
	}
	
	public void SetLocations(Player ply) {
		for (int i = 0; i < plys.size(); i++) {
			if (plys.get(i).equals(ply)) {
				plys.remove(i);
				plys.add(ply);
				return;
			}
		}
		plys.add(ply);
		System.out.println("player added");
	}
	
	public void Show() {// TODO
		System.out.println("===================================================");
		int[][] locations = new int[plys.size()][2];
		for (int i = 0; i < plys.size(); i++) {
			int[] boardLocation = LocationToIndex(plys.get(i).getPosition());
			locations[i] = boardLocation;
		}
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				boolean isPlayer = false;
				for (int k = 0; k < plys.size(); k++){
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

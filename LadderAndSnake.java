package lab1_comp249;

import java.util.Scanner;

public class LadderAndSnake {

	private int[][] board = new int[10][10];
	private String[][] aBoard = new String[][] {
		{"","","S","S","","S","","S","",""},
		{"","","","","","","","","",""},
		{"L","","","","","","","","","L"},
		{"","S","","S","","","","","",""},
		{"","","","","","","","","","L"},
		{"","","","","","","","S","",""},
		{"","","","","L","","","","",""},
		{"L","","","","","","","L","",""},
		{"","","","","S","","","","",""},
		{"L","","","L","","","","","L",""},
	
	};
	private String[] SnakeLadderData = new String[]{"0000,0302","0003,0106","0008,0309","0104,0005","0200,0401","0207,0903","0304,0403","0407,0209","0509,0606","0601,0101","0603,0500","0700,0900","0709,0909","0902,0702","0903,0704","0905,0203","0907,0607"};
	private String[] playerLocations; 
	private int nbPlayers;
	private int player1Pos;
	private int player2Pos;
	private int ladder = 500;
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
			createPlayerLocations(nbPlayers);
			this.nbPlayers = 2;
			players = new Player[2];
			return;
		}
		System.exit(0);
	}
	
	public LadderAndSnake() {
		this.nbPlayers = 2;
		players = new Player[2];
		createPlayerLocations(nbPlayers);
	}
	
	private String convertToLocation(int y, int x) {
		String xStr = (x>=10)? "0"+x : x+"";
		String yStr = (y>=10)? "0"+y : y+"";
		return yStr+xStr;
	}
	

	
	private void createPlayerLocations(int n) {
		playerLocations = new String[n];
		for (int i = 0; i < playerLocations.length; i++) {
			playerLocations[i] = "0000";
		}
	}
	
	private void assignPlayerLocations(int player, int y, int x) {
		player--;
		for (int i=0; i < playerLocations.length; i++) {
			if (i == player) {
				playerLocations[i] = convertToLocation(y,x);
				break;
			}
		}
	}

	
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
	
	
	private int flipDice() {
		final int min = 1;
		final int max = 6;
		final int range = max - min + 1;
		return (int)(Math.random() * range) + min;
	}
	
	private void getPlayOrder() {
		while(true) {
			int diceP1 = flipDice();
			int diceP2 = flipDice();
		}
	}

	
	private void reversePlayers() {
		Player temp = players[0];
		players[0] = players[1];
		players[1] = temp;
	}
	
	private int askUserToFill() {
		Scanner input = new Scanner(System.in);
		
		while (true) {
			System.out.println("It is Player " + players[0] +" Enter 'f' to flip the dice: ");
			if (input.hasNext()){
				if (!(input.next().equals("f"))) continue;
				reversePlayers();
				System.out.println("worked");
				break;
			}
		}
		
		return 0;
	}
	
	private void waitForInput(Player p) {
		if (!(LadderAndSnake.isManual)) return;
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("It is " + p.getName() + "'s turn. Press 'f'.");
			if (!(input.next().equals("f"))) continue;
			break;
		}
	}
	
	// plays the game
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
		Events event = new Events();
		System.out.println("------");
		while (!(Player.isGameDone)) {
			for (int i = 0; i < nbPlayers; i++) {
				int flip = flipDice();
				System.out.println(players[i].getName() + " rolled " + flip);
				LadderAndSnake.nbTurns++;
				players[i].updatePosition(flip);
				boardOut.SetLocations(players[i]);
				boardOut.Show();
			}
		}
	}

	public int getNbPlayers() {
		return nbPlayers;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	
}
	
	

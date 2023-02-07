package comp249_assignment1;
//-----------------------------------------------------
//Assignment 1
//Written by: Aniss Chalah 40251256, Zubeda Wajid Hameed 40246990
//-----------------------------------------------------


// The point of this program is to create a snakes and ladder game which consists of two players that take turns to roll the dice and have the objective of reaching the 100th position to win the game.
// This game uses the regular rules of a snakes and ladder game. Currently the game is set to two players but it can be modified to hold more players.
import java.io.IOException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		int n = 0;
		do {
			System.out.println("Please enter the number of players:");
			while(!(input.hasNextInt())) {
				System.out.println("Please insert a number: ");
				input.next();
			}
			n = input.nextInt();
			break;
		}while(true);
		//int n = input.nextInt();
		//creates a LadderAndSnake object.
		LadderAndSnake a = new LadderAndSnake(n);
		LadderAndSnake.isManual = false; // to 
		a.play();
		

		

	}

}

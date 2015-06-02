
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Game aGame = new Game();	//What kind of Game?
		
		aGame.add("Chet");	//Add WHAT?
		aGame.add("Pat");
		aGame.add("Sue");
		
		Random rand = new Random();
	
		do {
			//Order of operations not intention revealing
			//Can't tell what exactly the idea is behind wrongAnswer and wasCorrectlyAnswered.
			
			aGame.roll();
			
			if (rand.nextInt(9) == 7) {	//Sometimes answer wrongly?
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
			
			
			
		} while (notAWinner);
		
	}
}


package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	public static void main(String[] args) {
		Game aGame = new Game();	//What kind of Game?
		
		aGame.add("Chet");	//Add WHAT?
		aGame.add("Pat");
		aGame.add("Sue");
		
		Random rand = new Random();
	
		playGame(aGame, rand);
		
	}

	public static void playGame(Game aGame, Random rand) {
		do {
			//Order of operations not intention revealing
			//Can't tell what exactly the idea is behind wrongAnswer and wasCorrectlyAnswered.
			
			aGame.roll();
			
			if (shouldAnswerRight(rand)) {
				aGame.answerRight();
			} else {
				aGame.answerWrong();
			}
			
			aGame.nextPlayer();
		} while (!aGame.didLastPlayerWin());
	}

	private static boolean shouldAnswerRight(Random rand) {
		return rand.nextInt(9) != 7;
	}
}

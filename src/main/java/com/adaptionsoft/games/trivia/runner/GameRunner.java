
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	public static void main(String[] args) {
		Game game = new Game();
		
		game.addPlayer("Chet");
		game.addPlayer("Pat");
		game.addPlayer("Sue");
		
		Random random = new Random();

		run(game, random);
	}

	public static void run(Game game, Random rand) {
		boolean isWinner;
		do {

			game.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				game.wrongAnswer();
			} else {
				game.wasCorrectlyAnswered();
			}
			isWinner = game.isCurrentPlayerTheWinner();

			game.switchToNextPlayer();

		} while (!isWinner);
	}
}

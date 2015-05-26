package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class Game {

	private final Players players = new Players();
	private final Questions questions = new Questions();
	private final Screen screen;

	public Game() {
		this(System.out);
	}

	Game(PrintStream printStream) {
		screen = new Screen(printStream);
	}

	public void addPlayer(String playerName) {
		Player player = new Player(playerName);
		players.add(player);

		screen.printPlayerAdded(player);
		screen.printNumberOfPlayers(players);
	}

	public void roll(int roll) {
		Player currentPlayer = players.getCurrentPlayer();

		screen.printCurrentPlayer(currentPlayer);
		screen.printRoll(roll);

		if (currentPlayer.isInPenaltyBox()) {
			currentPlayer.setIsGettingOutOfPenaltyBoxByRoll(roll);
			screen.printIfGettingOutOfPenaltyBox(currentPlayer);
		}

		if (currentPlayer.isStuckInPenaltyBox()) {
			return;
		}

		currentPlayer.moveForwardBy(roll);

		screen.printPlayerLocation(currentPlayer);
		screen.printCategory(currentPlayer.getCurrentQuestionCategory());
		String nextQuestion = questions.pullNext(currentPlayer.getCurrentQuestionCategory());
		screen.printQuestion(nextQuestion);
	}

	public void wasCorrectlyAnswered() {
		Player currentPlayer = players.getCurrentPlayer();
		//TODO: method should not get called if stuck
		if (currentPlayer.isStuckInPenaltyBox()) {
			return ;
		}

		screen.printAnswerWasCorrect();
		currentPlayer.increasePurse();
		screen.printPurseOf(currentPlayer);
	}

	public boolean isCurrentPlayerTheWinner() {
		return players.getCurrentPlayer().isWinner();
	}

	public void switchToNextPlayer() {
		players.switchToNext();
	}

	public void wrongAnswer() {
		Player currentPlayer = players.getCurrentPlayer();
		screen.printAnswerWasWrong();
		screen.printWasSentToPenaltyBox(currentPlayer);
		currentPlayer.putInPenaltyBox();
	}

}

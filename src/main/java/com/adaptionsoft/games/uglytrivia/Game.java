package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class Game {
	PlayerPool playerPool = new PlayerPool();
	QuestionPool questionPool = new QuestionPool();

	private Screen screen;

	public Game() {
		this(System.out);
	}

	Game(PrintStream printStream) {
		screen = new Screen(printStream);
	}

	public boolean add(String playerName) {
		Player player = new Player(playerName);
		playerPool.addPlayer(player);

		screen.printPlayerAdded(playerName, playerPool.howManyPlayers());
		return true;
	}

	public void roll(int roll) {
		Player currentPlayer = playerPool.getCurrentPlayer();
		screen.printCurrentPlayer(currentPlayer.getName());
		screen.printRoll(roll);

		if (currentPlayer.isInPenaltyBox()) {
			currentPlayer.setGetOutOfPenaltyBox(roll);
			screen.printIsGettingOutOfPenaltyBox(currentPlayer.getName(), currentPlayer.isGettingOutOfPenaltyBox());
		} 
		
		if (currentPlayer.isInPenaltyBox() && !currentPlayer.isGettingOutOfPenaltyBox()) {
			return;
		}
		
		currentPlayer.addPlace(roll);
		screen.printNewLocationInfo(currentPlayer.getName(), currentPlayer.getPlace(), currentCategory());
		
		String question = questionPool.getQuestion(currentCategory());
		screen.printQuestion(question);
	}

	private QuestionCategory currentCategory() {
		int place = playerPool.getCurrentPlayer().getPlace();
		if (place % 4 == 0)
			return QuestionCategory.POP;
		if (place % 4 == 1)
			return QuestionCategory.SCIENCE;
		if (place % 4 == 2)
			return QuestionCategory.SPORTS;
		return QuestionCategory.ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		Player currentPlayer = playerPool.getCurrentPlayer();
		if (currentPlayer.isInPenaltyBox()
				&& !currentPlayer.isGettingOutOfPenaltyBox()) {
			playerPool.nextPlayer();
			return true;
		}
		
		currentPlayer.addPurse();
		screen.printCorrectAnswerInfo(currentPlayer.getName(), currentPlayer.getPurse());
		
		playerPool.nextPlayer();
		
		return !currentPlayer.didPlayerWin();
	}

	public boolean wrongAnswer() {
		Player currentPlayer = playerPool.getCurrentPlayer();
		screen.printWrongAnswerInfo(currentPlayer.getName());
		currentPlayer.putIntoPenaltyBox();

		playerPool.nextPlayer();
		return true;
	}
}

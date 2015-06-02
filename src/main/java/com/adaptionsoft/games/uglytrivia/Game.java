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
			if (roll % 2 != 0) {
				screen.printIsGettingOutOfPenaltyBox(currentPlayer.getName());
				advancePlayerAndAskQuestion(roll);
			} else {
				screen.printIsNotGettingOutOfPenaltyBox(currentPlayer.getName());
			}
		} else {
			advancePlayerAndAskQuestion(roll);
		}
	}

	private void advancePlayerAndAskQuestion(int roll) {
		Player currentPlayer = playerPool.getCurrentPlayer();
		currentPlayer.addPlace(roll);

		screen.printNewLocationInfo(currentPlayer.getName(), currentPlayer.getPlace(), currentCategory());
		askQuestion();
	}

	private void askQuestion() {
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
		return addPursesAndDidPlayerWin();
	}

	private boolean addPursesAndDidPlayerWin() {
		Player currentPlayer = playerPool.getCurrentPlayer();
		currentPlayer.addPurse();
		screen.printCorrectAnswerInfo(currentPlayer.getName(), currentPlayer.getPurse());

		boolean notWinner = !currentPlayer.didPlayerWin();
		playerPool.nextPlayer();

		return notWinner;
	}

	public boolean wrongAnswer() {
		Player currentPlayer = playerPool.getCurrentPlayer();
		screen.printWrongAnswerInfo(currentPlayer.getName());
		currentPlayer.putIntoPenaltyBox();

		playerPool.nextPlayer();
		return true;
	}
}

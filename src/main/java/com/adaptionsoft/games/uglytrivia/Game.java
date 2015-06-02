package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.Random;

public class Game {
	PlayerPool playerPool = new PlayerPool();
	QuestionPool questionPool = new QuestionPool();

	private Screen screen;
	private Random random;

	public Game() {
		this(System.out, new Random());
	}

	Game(PrintStream printStream, Random random) {
		screen = new Screen(printStream);
		this.random = random;
	}

	public boolean add(String playerName) {
		Player player = new Player(playerName);
		playerPool.addPlayer(player);

		screen.printPlayerAdded(playerName, playerPool.howManyPlayers());
		return true;
	}

	public void roll() {
		int roll = random.nextInt(5) + 1;
		
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
		screen.printNewLocationInfo(currentPlayer.getName(), currentPlayer.getPlace(), currentPlayer.currentCategory());
		
		String question = questionPool.getQuestion(currentPlayer.currentCategory());
		screen.printQuestion(question);
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

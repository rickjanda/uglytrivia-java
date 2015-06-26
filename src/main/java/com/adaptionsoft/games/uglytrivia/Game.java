package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.Random;

public class Game {
	public PlayerPool playerPool = new PlayerPool();
	QuestionPool questionPool = new QuestionPool();

	public Screen screen;
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

	public int roll() {
		int roll = random.nextInt(5) + 1;
		
		screen.printCurrentPlayer(playerPool.getCurrentPlayer().getName());
		screen.printRoll(roll);
		return roll;
	}
	
	public boolean isPlayerStuckInPenaltyBox(int roll) {
		Player currentPlayer = playerPool.getCurrentPlayer();
		if (currentPlayer.isInPenaltyBox()) {
			currentPlayer.setGetOutOfPenaltyBox(roll);
			screen.printIsGettingOutOfPenaltyBox(currentPlayer.getName(), currentPlayer.isGettingOutOfPenaltyBox());
		} 
		
		return currentPlayer.isStuckInPenaltyBox();
	}

	public void moveAndAskQuestion(int roll) {
		Player currentPlayer = playerPool.getCurrentPlayer();
		currentPlayer.addPlace(roll);
		screen.printNewLocationInfo(currentPlayer.getName(), currentPlayer.getPlace(), currentPlayer.currentCategory());
		
		String question = questionPool.getQuestion(currentPlayer.currentCategory());
		screen.printQuestion(question);
	}

	public void answerRight() {
		Player currentPlayer = playerPool.getCurrentPlayer();
		if (!currentPlayer.isStuckInPenaltyBox()) {
			currentPlayer.addPurse();
			screen.printCorrectAnswerInfo(currentPlayer.getName(), currentPlayer.getPurse());
		
		}
	}
	
	public void answerWrong() {
		Player currentPlayer = playerPool.getCurrentPlayer();
		screen.printWrongAnswerInfo(currentPlayer.getName());
		currentPlayer.putIntoPenaltyBox();
	}
	
	public void nextPlayer() {
		playerPool.nextPlayer();
	}
	
	public boolean didLastPlayerWin() {
		return playerPool.getLastPlayer().didPlayerWin();
	}
}

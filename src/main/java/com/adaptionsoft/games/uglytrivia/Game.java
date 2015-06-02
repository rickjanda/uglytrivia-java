package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class Game {
	PlayerPool playerPool = new PlayerPool();
	QuestionPool questionPool = new QuestionPool();

	private PrintStream out;

	public Game() {
		this(System.out);
	}

	Game(PrintStream printStream) {
		out = printStream;
	}

	public boolean add(String playerName) {
		Player player = new Player(playerName);
		playerPool.addPlayer(player);

		out.println(playerName + " was added");
		out.println("They are player number " + playerPool.howManyPlayers());
		return true;
	}

	public void roll(int roll) {
		Player currentPlayer = playerPool.getCurrentPlayer();
		out.println(currentPlayer.getName() + " is the current player");
		out.println("They have rolled a " + roll);

		if (currentPlayer.isInPenaltyBox()) {
			currentPlayer.setGetOutOfPenaltyBox(roll);
			if (roll % 2 != 0) {
				out.println(currentPlayer.getName()
						+ " is getting out of the penalty box");
				advancePlayerAndAskQuestion(roll);
			} else {
				out.println(currentPlayer.getName()
						+ " is not getting out of the penalty box");
			}
		} else {
			advancePlayerAndAskQuestion(roll);
		}
	}

	private void advancePlayerAndAskQuestion(int roll) {
		Player currentPlayer = playerPool.getCurrentPlayer();
		currentPlayer.addPlace(roll);

		out.println(currentPlayer.getName() + "'s new location is "
				+ currentPlayer.getPlace());
		out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void askQuestion() {
		String removeFirst = questionPool.getQuestion(currentCategory());
		out.println(removeFirst);
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
		out.println("Answer was correct!!!!");
		Player currentPlayer = playerPool.getCurrentPlayer();
		currentPlayer.addPurse();
		out.println(currentPlayer.getName() + " now has "
				+ currentPlayer.getPurse() + " Gold Coins.");

		boolean notWinner = !currentPlayer.didPlayerWin();
		playerPool.nextPlayer();

		return notWinner;
	}

	public boolean wrongAnswer() {
		out.println("Question was incorrectly answered");
		out.println(playerPool.getCurrentPlayer().getName()
				+ " was sent to the penalty box");
		playerPool.getCurrentPlayer().putIntoPenaltyBox();

		playerPool.nextPlayer();
		return true;
	}
}

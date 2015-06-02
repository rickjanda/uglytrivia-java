package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	ArrayList<Player> players = new ArrayList<>();

	// duplicate
	LinkedList popQuestions = new LinkedList();
	LinkedList scienceQuestions = new LinkedList();
	LinkedList sportsQuestions = new LinkedList();
	LinkedList rockQuestions = new LinkedList();

	int currentPlayerIndex = 0;

	private PrintStream out;

	public Game() {
		this(System.out);
	}

	Game(PrintStream printStream) {
		out = printStream;
		for (int i = 0; i < 50; i++) {
			// duplicate
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i)); // Inline
		}

	}

	private String createRockQuestion(int index) {
		return "Rock Question " + index;
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public void addPlayer(String playerName) {
		Player player = new Player(playerName);
		players.add(player);

		out.println(playerName + " was added");
		out.println("They are player number " + howManyPlayers());
	}

	private int howManyPlayers() {
		return players.size();
	}

	public void rollDice(int roll) {
		Player currentPlayer = getCurrentPlayer();
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

	private Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	private void advancePlayerAndAskQuestion(int roll) {
		Player currentPlayer = getCurrentPlayer();
		currentPlayer.addPlace(roll);

		out.println(currentPlayer.getName() + "'s new location is "
				+ currentPlayer.getPlace());
		out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void askQuestion() {
		// duplicate zweizeiler
		if (currentCategory() == QuestionCategory.POP)
			out.println(popQuestions.removeFirst());
		if (currentCategory() == QuestionCategory.SCIENCE)
			out.println(scienceQuestions.removeFirst());
		if (currentCategory() == QuestionCategory.SPORTS)
			out.println(sportsQuestions.removeFirst());
		if (currentCategory() == QuestionCategory.ROCK)
			out.println(rockQuestions.removeFirst());
	}

	private QuestionCategory currentCategory() {
		int place = getCurrentPlayer().getPlace();
		if (place % 4 == 0)
			return QuestionCategory.POP;
		if (place % 4 == 1)
			return QuestionCategory.SCIENCE;
		if (place % 4 == 2)
			return QuestionCategory.SPORTS;
		return QuestionCategory.ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		Player currentPlayer = getCurrentPlayer();
		if (currentPlayer.isInPenaltyBox()
				&& !currentPlayer.isGettingOutOfPenaltyBox()) {
			nextPlayer();
			return true;
		}
		return addPursesAndDidPlayerWin();
	}

	private boolean addPursesAndDidPlayerWin() {
		out.println("Answer was correct!!!!");
		Player currentPlayer = getCurrentPlayer();
		currentPlayer.addPurse();
		out.println(currentPlayer.getName() + " now has "
				+ currentPlayer.getPurse() + " Gold Coins.");

		boolean notWinner = !getCurrentPlayer().didPlayerWin();
		nextPlayer();

		return notWinner;
	}

	private void nextPlayer() {
		currentPlayerIndex++;
		if (currentPlayerIndex == howManyPlayers())
			currentPlayerIndex = 0;
	}

	public boolean wrongAnswer() {
		out.println("Question was incorrectly answered");
		out.println(getCurrentPlayer().getName()
				+ " was sent to the penalty box");
		getCurrentPlayer().putIntoPenaltyBox();

		nextPlayer();
		return true;
	}
}

package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class Screen {
	public PrintStream out;

	public Screen(PrintStream printStream) {
		out = printStream;
	}

	void printPlayerAdded(String playerName, int howManyPlayers) {
		out.println(playerName + " was added");
		out.println("They are player number " + howManyPlayers);
	}

	void printCurrentPlayer(String name) {
		out.println(name + " is the current player");
	}

	void printRoll(int roll) {
		out.println("They have rolled a " + roll);
	}

	void printNewLocationInfo(String name, int place,
			QuestionCategory currentCategory) {
		out.println(name + "'s new location is " + place);
		out.println("The category is " + currentCategory);
	}

	void printQuestion(String question) {
		out.println(question);
	}

	void printCorrectAnswerInfo(String name, int purse) {
		out.println("Answer was correct!!!!");
		out.println(name + " now has " + purse + " Gold Coins.");
	}

	void printWrongAnswerInfo(String name) {
		out.println("Question was incorrectly answered");
		out.println(name + " was sent to the penalty box");
	}

	void printIsGettingOutOfPenaltyBox(String name,
			boolean gettingOutOfPenaltyBox) {
		if (!gettingOutOfPenaltyBox) {
			out.println(name + " is not getting out of the penalty box");

		} else {
			out.println(name + " is getting out of the penalty box");
		}
	}
}
package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.Questions.Category;

import java.io.PrintStream;

public class Screen {
    final PrintStream out;

    public Screen(PrintStream printStream) {
        out = printStream;
    }

    public void printPlayerAdded(Player player) {
        out.println(player.getName() + " was added");
    }

    public void printNumberOfPlayers(Players players) {
        out.println("They are player number " + players.size());
    }

    public void printCurrentPlayer(Player currentPlayer) {
        out.println(currentPlayer.getName() + " is the current player");
    }

    public void printRoll(int roll) {
        out.println("They have rolled a " + roll);
    }

    public void printPlayerLocation(Player player) {
        out.println(player.getName() + "'s new location is " + player.getPlace());
    }

    public void printPurseOf(Player player) {
        out.println(player.getName() + " now has " + player.getPurse() + " Gold Coins.");
    }

    public void printAnswerWasCorrect() {
        out.println("Answer was correct!!!!");
    }

    public void printAnswerWasWrong() {
        out.println("Question was incorrectly answered");
    }

    public void printWasSentToPenaltyBox(Player currentPlayer) {
        out.println(currentPlayer.getName() + " was sent to the penalty box");
    }

    public void printCategory(Category currentQuestionCategory) {
        out.println("The category is " + currentQuestionCategory);
    }

    public void printIfGettingOutOfPenaltyBox(Player currentPlayer) {
        if (currentPlayer.isGettingOutOfPenaltyBox()) {
            out.println(currentPlayer.getName() + " is getting out of the penalty box");
        } else {
            out.println(currentPlayer.getName() + " is not getting out of the penalty box");
        }
    }

    public void printQuestion(String question) {
        out.println(question);
    }
}

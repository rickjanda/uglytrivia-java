package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	ArrayList<Player> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

	// duplicate
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

	private PrintStream out;

	public  Game() {
		this(System.out);
	}

	Game(PrintStream printStream) {
		out = printStream;
		for (int i = 0; i < 50; i++) {
			// duplicate
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));	//Inline
		}

	}

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		Player player = new Player(playerName);
	    players.add(player);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    out.println(playerName + " was added");
	    out.println("They are player number " + howManyPlayers());
		return true;
	}

	private int howManyPlayers() {
		return players.size();
	}
	
	public void roll(int roll) {
		out.println(getCurrentPlayer() + " is the current player");
		out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				out.println(getCurrentPlayer() + " is getting out of the penalty box");
				advancePlayerAndAskQuestion(roll);
			} else {
				out.println(getCurrentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
			advancePlayerAndAskQuestion(roll);
		}
		
	}

	private String getCurrentPlayer() {
		return players.get(currentPlayer).getName();
	}

	void advancePlayerAndAskQuestion(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
		
		out.println(getCurrentPlayer()
				+ "'s new location is "
				+ places[currentPlayer]);
		out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void askQuestion() {
		//duplicate zweizeiler
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
		if (places[currentPlayer] % 4 == 0) return QuestionCategory.POP;
		if (places[currentPlayer] % 4 == 1) return QuestionCategory.SCIENCE;
		if (places[currentPlayer] % 4 == 2) return QuestionCategory.SPORTS;
		return QuestionCategory.ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox){
			nextPlayer();
			return true;
		}
		return addPursesAndDidPlayerWin();
	}

	private boolean addPursesAndDidPlayerWin() {
		out.println("Answer was correct!!!!");
		purses[currentPlayer]++;
		out.println(getCurrentPlayer()
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");
		
		boolean winner = didPlayerWin();
		nextPlayer();
		
		return winner;
	}
	
	private void nextPlayer() {
		currentPlayer++;
		if (currentPlayer == howManyPlayers()) currentPlayer = 0;
	}

	public boolean wrongAnswer(){
		out.println("Question was incorrectly answered");
		out.println(getCurrentPlayer() + " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		nextPlayer();
		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}

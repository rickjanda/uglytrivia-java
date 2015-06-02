package com.adaptionsoft.games.uglytrivia;

public class Player {

	private String playerName;
	private int place;
	private int purse;
	private boolean inPenaltyBox;
	private boolean isGettingOutOfPenaltyBox;

	public Player(String playerName) {
		this.playerName = playerName;
		place = 0;
		purse = 0;
		inPenaltyBox = false;
		isGettingOutOfPenaltyBox = false;
	}

	public String getName() {
		return playerName;
	}

	public int getPlace() {
		return place;
	}
	
	public int getPurse() {
		return purse;
	}

	public void addPlace(int roll) {
		place = (place + roll) % 12;
	}

	public void addPurse() {
		purse++;
	}
	
	public boolean isInPenaltyBox() {
		return inPenaltyBox;
	}
	
	public boolean isGettingOutOfPenaltyBox() {
		return isGettingOutOfPenaltyBox;
	}

	public void putIntoPenaltyBox() {
		inPenaltyBox = true;
	}

	public void setGetOutOfPenaltyBox(int roll) {
		isGettingOutOfPenaltyBox = roll % 2 != 0;
	}

	boolean didPlayerWin() {
		return getPurse() == 6;
	}

	QuestionCategory currentCategory() {
		int place = getPlace();
		if (place % 4 == 0)
			return QuestionCategory.POP;
		if (place % 4 == 1)
			return QuestionCategory.SCIENCE;
		if (place % 4 == 2)
			return QuestionCategory.SPORTS;
		return QuestionCategory.ROCK;
	}
}

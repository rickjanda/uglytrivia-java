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
}

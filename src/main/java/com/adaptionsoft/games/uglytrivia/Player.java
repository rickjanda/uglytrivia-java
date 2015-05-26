package com.adaptionsoft.games.uglytrivia;

public class Player {

	private String playerName;
	private int place;
	private int purse;
	private boolean inPenaltyBox;

	public Player(String playerName) {
		this.playerName = playerName;
		place = 0;
		purse = 0;
		inPenaltyBox = false;
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

	public void putIntoPenaltyBox() {
		inPenaltyBox = true;
	}
}

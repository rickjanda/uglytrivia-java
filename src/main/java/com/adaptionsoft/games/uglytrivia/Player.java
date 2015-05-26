package com.adaptionsoft.games.uglytrivia;

public class Player {

	private String playerName;
	private int place;
	private int purse;

	public Player(String playerName) {
		this.playerName = playerName;
		place = 0;
		purse = 0;
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
}

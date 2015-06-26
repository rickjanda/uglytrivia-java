package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class PlayerPool {
	private ArrayList<Player> players;
	private int currentPlayerIndex;
	private int lastPlayerIndex;

	public PlayerPool() {
		this.players = new ArrayList<Player>();
		currentPlayerIndex = 0;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public int howManyPlayers() {
		return players.size();
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	public Player getLastPlayer() {
		return players.get(lastPlayerIndex);
	}

	public void nextPlayer() {
		lastPlayerIndex = currentPlayerIndex;
		currentPlayerIndex++;
		if (currentPlayerIndex == players.size())
			currentPlayerIndex = 0;
	}

}
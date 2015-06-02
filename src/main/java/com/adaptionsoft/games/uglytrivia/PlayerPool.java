package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class PlayerPool {
	private ArrayList<Player> players;
	private int currentPlayerIndex;

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

	public void nextPlayer() {
		currentPlayerIndex++;
		if (currentPlayerIndex == howManyPlayers())
			currentPlayerIndex = 0;
	}

}
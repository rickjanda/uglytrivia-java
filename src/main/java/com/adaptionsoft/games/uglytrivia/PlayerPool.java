package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class PlayerPool {
	public ArrayList<Player> players;
	public int currentPlayerIndex;

	public PlayerPool() {
		this.players = new ArrayList<Player>();
		currentPlayerIndex = 0;
	}

	void addPlayer(Player player) {
		players.add(player);
	}

	int howManyPlayers() {
		return players.size();
	}

	Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	void nextPlayer() {
		currentPlayerIndex++;
		if (currentPlayerIndex == howManyPlayers())
			currentPlayerIndex = 0;
	}

}
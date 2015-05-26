package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {

    public ArrayList<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public Players() {
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void add(Player player) {
        players.add(player);
    }

    public int size() {
        return players.size();
    }

    public void switchToNext() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}

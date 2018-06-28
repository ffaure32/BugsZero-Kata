package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Players {
    private int currentPlayerIndex = 0;
    private List<Player> players = new ArrayList<>();

    public Players(String... playerNames) {
        verifyGameValidity(playerNames);

        addPlayers(playerNames);
    }

    private void verifyGameValidity(String[] players) {
        if(players.length<2 || players.length>=7) {
            throw new IllegalArgumentException("nombre de joueurs interdit");
        }
    }

    private void addPlayers(String[] players) {
        Arrays.stream(players).forEach(this::addPlayer);
    }

    private void addPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    public void nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) {
            currentPlayerIndex = 0;
        }
    }

    public boolean hasWinner() {
        return players.stream().anyMatch(Player::isWinner);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

}

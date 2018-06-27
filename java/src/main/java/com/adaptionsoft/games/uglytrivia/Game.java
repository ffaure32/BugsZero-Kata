package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private List<Player> players = new ArrayList<>();

    private int currentPlayerIndex = 0;

    private QuestionDeck questionDeck;

    public Game(String ... players) {
        verifyGameValidity(players);
        addPlayers(players);
        initQuestionDeck();
    }

    private void initQuestionDeck() {
        questionDeck = new QuestionDeck(50);
    }

    private void addPlayers(String[] players) {
        Arrays.stream(players).forEach(this::addPlayer);
    }

    private void verifyGameValidity(String[] players) {
        if(players.length<2 || players.length>=7) {
            throw new IllegalArgumentException("nombre de joueurs interdit");
        }
    }

    private void addPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    public void tryToMove(Roll roll) {
        tryToEscapePenaltyBox(roll);
        if (!isCurrentPlayerInPenaltyBox()) {
            movePlayer(roll);
            askQuestion();
        }

    }

    private void tryToEscapePenaltyBox(Roll roll) {
        if (isCurrentPlayerInPenaltyBox()) {
            Player currentPlayer = getCurrentPlayer();
            if (roll.deliverFromPenaltyBox()) {
                currentPlayer.escapePenaltyBox();
            }
        }
    }

    public boolean isCurrentPlayerInPenaltyBox() {
        return getCurrentPlayer().isInPenaltyBox();
    }

    private void movePlayer(Roll roll) {
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.move(roll);
    }

    private void askQuestion() {
        questionDeck.nextQuestion(currentCategory());
    }

    private Category currentCategory() {
        int place = getCurrentPlayer().getPlace();
        return Category.get(place);
    }

    public void wasCorrectlyAnswered() {
        updatePurse();
    }

    private void updatePurse() {
        Player player = getCurrentPlayer();
        player.addReward();
    }

    public void wrongAnswer() {
        getCurrentPlayer().sendToPenaltyBox();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
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

}

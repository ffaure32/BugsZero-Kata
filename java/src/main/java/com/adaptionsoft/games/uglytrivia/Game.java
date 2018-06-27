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

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void tryToMove(int roll) {
        System.out.println(getCurrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);

        tryToEscapePenaltyBox(roll);
        if (!isCurrentPlayerInPenaltyBox()) {
            movePlayer(roll);
            askQuestion();
        }

    }

    private void tryToEscapePenaltyBox(int roll) {
        if (isCurrentPlayerInPenaltyBox()) {
            Player currentPlayer = getCurrentPlayer();
            if (roll % 2 != 0) {
                System.out.println(currentPlayer + " is getting out of the penalty box");
                currentPlayer.escapePenaltyBox();
            } else {
                System.out.println(currentPlayer + " is not getting out of the penalty box");
            }
        }
    }

    public boolean isCurrentPlayerInPenaltyBox() {
        return getCurrentPlayer().isInPenaltyBox();
    }

    private void movePlayer(int roll) {
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.move(roll);

        System.out.println(currentPlayer
            + "'s new location is "
            + currentPlayer.getPlace());
        System.out.println("The category is " + currentCategory());
    }

    private void askQuestion() {
        System.out.println(questionDeck.nextQuestion(currentCategory()));
    }

    private Category currentCategory() {
        int place = getCurrentPlayer().getPlace();
        return Category.get(place);
    }

    public void wasCorrectlyAnswered() {
        updatePurse();
    }

    private void updatePurse() {
        System.out.println("Answer was correct!!!!");
        Player player = getCurrentPlayer();
        player.addReward();
        System.out.println(player
            + " now has "
            + player.getPurse()
            + " Gold Coins.");
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayer() + " was sent to the penalty box");
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

package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

public class Game {
    private QuestionDeck questionDeck;
    private Dice dice;
    private Random random;

    private Players players;

    public Game(QuestionDeck questionDeck, Dice dice, String... playerNames) {
        this.questionDeck = questionDeck;
        this.dice = dice;
        players = new Players(playerNames);
        random = new Random();
    }

    public void playRound() {
        tryToMove(dice.roll());
        if(!getCurrentPlayer().isInPenaltyBox()) {
            playQuestion();
        }
        players.nextPlayer();
    }

    private void playQuestion() {
        getCurrentPlayer().playQuestion(askQuestion(), random.nextInt(Question.ANSWERS_COUNT));
    }

    public void tryToMove(Roll roll) {
        tryToEscapePenaltyBox(roll);
        movePlayer(roll);
    }

    private void tryToEscapePenaltyBox(Roll roll) {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.isInPenaltyBox()) {
            if (roll.deliverFromPenaltyBox()) {
                currentPlayer.escapePenaltyBox();
            }
        }
    }

    private void movePlayer(Roll roll) {
        Player currentPlayer = getCurrentPlayer();
        if (!currentPlayer.isInPenaltyBox()) {
            currentPlayer.move(roll);
        }
    }

    private Question askQuestion() {
        return questionDeck.nextQuestion(currentCategory());
    }

    private Category currentCategory() {
        int place = getCurrentPlayer().getPlace();
        return Category.get(place);
    }

    public boolean hasWinner() {
        return players.hasWinner();
    }

    public Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }
}

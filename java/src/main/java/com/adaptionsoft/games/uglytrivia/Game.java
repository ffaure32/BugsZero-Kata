package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

public class Game {
    private final QuestionDeck questionDeck;
    private final Dice dice;
    private final Random random;
    private final Players players;

    public Game(QuestionDeck questionDeck, Dice dice, String... playerNames) {
        this.questionDeck = questionDeck;
        this.dice = dice;
        players = new Players(playerNames);
        random = new Random();
    }

    public void playRound() {
        tryToMove(dice.roll());
        tryToAskQuestion();
        players.nextPlayer();
    }

    private void tryToAskQuestion() {
        if(!getCurrentPlayer().isInPenaltyBox()) {
            playQuestion();
        }
    }

    private void playQuestion() {
        Question question = questionDeck.nextQuestion(currentCategory());
        getCurrentPlayer().playQuestion(question, random.nextInt(Question.ANSWERS_COUNT));
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

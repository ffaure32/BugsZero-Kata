package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

public class Player {

    private final String name;
    private Place place = new Place();
    private int purse = 0;
    private boolean inPenaltyBox = false;
    private Random random = new Random();

    public Player(String name) {
        this.name = name;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void move(Roll roll) {
        place.move(roll);
    }

    public int getPlace() {
        return place.getPosition();
    }

    public void addReward() {
        purse++;
    }

    public int getPurse() {
        return purse;
    }

    public void sendToPenaltyBox() {
        inPenaltyBox = true;
    }

    public void escapePenaltyBox() {
        inPenaltyBox = false;
    }

    boolean isWinner() {
        return purse == 6;
    }

    public void playQuestion(Question question, int answerIndex) {
        boolean correctAnswer = question.verifyAnswer(answerIndex);
        if (correctAnswer) {
            addReward();
        } else {
            sendToPenaltyBox();
        }
    }


}

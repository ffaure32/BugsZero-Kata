package com.adaptionsoft.games.uglytrivia;

public class Player {

    private static final int TRAY_SIZE = 12;
    private final String name;
    private int place = 0;
    private int purse = 0;
    private boolean inPenaltyBox = false;


    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void move(int roll) {
        place = place + roll;
        if (place >= TRAY_SIZE) {
            place = place - TRAY_SIZE;
        }

    }

    public int getPlace() {
        return place;
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
}

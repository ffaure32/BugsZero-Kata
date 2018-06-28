package com.adaptionsoft.games.uglytrivia;

public class Place {
    private static final int TRAY_SIZE = 12;

    private int position = 0;

    public void move(Roll roll) {
        position = position + roll.face;
        if (position >= TRAY_SIZE) {
            position = position - TRAY_SIZE;
        }

    }

    public int getPosition() {
        return position;
    }


}

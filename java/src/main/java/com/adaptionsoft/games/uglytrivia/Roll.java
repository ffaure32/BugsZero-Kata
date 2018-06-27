package com.adaptionsoft.games.uglytrivia;

public class Roll {

    public final int face;

    public Roll(int face) {
        this.face = face;
    }

    public boolean deliverFromPenaltyBox() {
        return face % 2 != 0;
    }


}

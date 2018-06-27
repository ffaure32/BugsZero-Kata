package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

public class Dice {
    final Random rand;
    final int diceFaces;

    public Dice(Random rand, int diceFaces) {
        this.rand = rand;
        this.diceFaces = diceFaces;
    }

    public Roll roll() {
        return new Roll(rand.nextInt(diceFaces-1) + 1);
    }
}

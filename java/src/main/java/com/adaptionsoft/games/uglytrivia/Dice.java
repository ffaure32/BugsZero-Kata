package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

public class Dice {

    private static final int DEFAULT_FACES = 6;
    final Random rand;
    final int diceFaces;

    public Dice(Random rand) {
        this(rand, DEFAULT_FACES);
    }

    public Dice(Random rand, int diceFaces) {
        this.rand = rand;
        this.diceFaces = diceFaces;
    }

    public Roll roll() {
        return new Roll(rand.nextInt(diceFaces-1) + 1);
    }
}

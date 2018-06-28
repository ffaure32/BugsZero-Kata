package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;
import org.junit.Test;
import org.mockito.Mockito;

public class DiceTest {

    @Test
    public void roll() {
        int dicefaces = 6;
        int diceResult = 3;
        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt(dicefaces-1)).thenReturn(diceResult);

        Dice dice = new Dice(mockRandom, dicefaces);
        Roll result = dice.roll();

        assertEquals(diceResult+1, result.face);
    }
}
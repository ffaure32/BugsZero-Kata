package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.*;

import org.junit.Test;

public class RollTest {
    @Test
    public void oddRollDeliversFromPenaltyBox() {
        Roll roll = new Roll(1);

        assertTrue(roll.deliverFromPenaltyBox());
    }

    @Test
    public void evenRollKeepInPenaltyBox() {
        Roll roll = new Roll(2);

        assertFalse(roll.deliverFromPenaltyBox());
    }

}
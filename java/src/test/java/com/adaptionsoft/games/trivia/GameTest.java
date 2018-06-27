package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.Game;
import org.approvaltests.Approvals;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.Random;
import java.util.stream.IntStream;

public class GameTest {

	@Test
    @Ignore
	public void itsLockedDown() throws Exception {

        Random randomizer = new Random(123455);
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        IntStream.range(1,15).forEach(i -> GameRunner.playGame(randomizer));

        Approvals.verify(resultStream.toString());

	}

	@Test(expected = IllegalArgumentException.class)
    public void testInitGameWithTooFewPlayers() {
	    Game game = new Game("joueur1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitGameWithTooManyPlayers() {
        Game game = new Game("joueur1", "joueur2", "joueur3", "joueur4", "joueur5", "joueur6", "joueur7");
    }

    @Test
    public void testInitGameOK() {
        Game game = new Game("joueur1", "joueur2");

        assertFalse(game.hasWinner());
        assertEquals("joueur1", game.getCurrentPlayer().toString());
    }

    @Test
    public void testFirstMove() {
        Game game = new Game("joueur1", "joueur2");

        game.tryToMove(3);
        assertEquals(3, game.getCurrentPlayer().getPlace());
    }

    @Test
    public void testEvenDiceDoesNotFreePrisoner() {
        Game game = new Game("joueur1", "joueur2");
        game.getCurrentPlayer().sendToPenaltyBox();

        game.tryToMove(2);

        assertTrue(game.isCurrentPlayerInPenaltyBox());
        assertEquals(0, game.getCurrentPlayer().getPlace());
    }

    @Test
    public void testOddDiceDoesNotFreePrisoner() {
        Game game = new Game("joueur1", "joueur2");
        game.getCurrentPlayer().sendToPenaltyBox();

        game.tryToMove(3);
        assertFalse(game.isCurrentPlayerInPenaltyBox());
        assertEquals(3, game.getCurrentPlayer().getPlace());
    }

    @Test
    public void rightAnswerIncreasePurse() {
        Game game = new Game("joueur1", "joueur2");
        game.wasCorrectlyAnswered();

        assertEquals(1, game.getCurrentPlayer().getPurse());
    }

    @Test
    public void wrongAnswerSendToPenaltyBox() {
        Game game = new Game("joueur1", "joueur2");
        game.wrongAnswer();

        assertTrue(game.isCurrentPlayerInPenaltyBox());
        assertEquals(0, game.getCurrentPlayer().getPurse());
    }

    @Test
    public void testNextPlayer() {
        Game game = new Game("joueur1", "joueur2");
        game.nextPlayer();

        assertEquals("joueur2", game.getCurrentPlayer().toString());
    }

    @Test
    public void testBackToFirstPlayer() {
        Game game = new Game("joueur1", "joueur2");
        game.nextPlayer();
        game.nextPlayer();

        assertEquals("joueur1", game.getCurrentPlayer().toString());
    }

    @Test
    public void testGameWin() {
        Game game = new Game("joueur1", "joueur2");

        IntStream.range(0, 6).forEach(i -> game.wasCorrectlyAnswered());

        assertTrue(game.hasWinner());
    }

}

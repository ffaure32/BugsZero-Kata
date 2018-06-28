package com.adaptionsoft.games.trivia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.adaptionsoft.games.uglytrivia.Dice;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;
import com.adaptionsoft.games.uglytrivia.Players;
import com.adaptionsoft.games.uglytrivia.QuestionDeck;
import com.adaptionsoft.games.uglytrivia.Roll;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.Test;

public class GameTest {
    private Random random = new Random();
    private Dice dice = new Dice(random, 6);
    private QuestionDeck questionDeck = new QuestionDeck(50);

    @Test(expected = IllegalArgumentException.class)
    public void testInitGameWithTooFewPlayers() {
	    Game game = new Game(questionDeck, dice, "joueur1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitGameWithTooManyPlayers() {
        Game game = new Game(questionDeck, dice,"joueur1", "joueur2", "joueur3", "joueur4", "joueur5", "joueur6", "joueur7");
    }

    @Test
    public void testInitGameOK() {
        Players players = new Players("joueur1", "joueur2");

        assertFalse(players.hasWinner());
        assertEquals("joueur1", players.getCurrentPlayer().toString());
    }

    @Test
    public void testFirstMove() {
        Game game = new Game(questionDeck, dice,"joueur1", "joueur2");

        game.tryToMove(new Roll(3));
        assertEquals(3, game.getCurrentPlayer().getPlace());
    }

    @Test
    public void testEvenDiceDoesNotFreePrisoner() {
        Game game = new Game(questionDeck, dice,"joueur1", "joueur2");
        game.getCurrentPlayer().sendToPenaltyBox();

        game.tryToMove(new Roll(2));

        assertTrue(game.getCurrentPlayer().isInPenaltyBox());
        assertEquals(0, game.getCurrentPlayer().getPlace());
    }

    @Test
    public void testOddDiceDoesNotFreePrisoner() {
        Game game = new Game(questionDeck, dice,"joueur1", "joueur2");
        game.getCurrentPlayer().sendToPenaltyBox();

        game.tryToMove(new Roll(3));
        assertFalse(game.getCurrentPlayer().isInPenaltyBox());
        assertEquals(3, game.getCurrentPlayer().getPlace());
    }

    @Test
    public void wrongAnswerSendToPenaltyBox() {
        Game game = new Game(questionDeck, dice,"joueur1", "joueur2");
        game.getCurrentPlayer().sendToPenaltyBox();

        assertTrue(game.getCurrentPlayer().isInPenaltyBox());
        assertEquals(0, game.getCurrentPlayer().getPurse());
    }

    @Test
    public void testNextPlayer() {
        Players players = new Players("joueur1", "joueur2");
        players.nextPlayer();

        assertEquals("joueur2", players.getCurrentPlayer().toString());
    }

    @Test
    public void testBackToFirstPlayer() {
        Players players = new Players("joueur1", "joueur2");
        players.nextPlayer();
        players.nextPlayer();

        assertEquals("joueur1", players.getCurrentPlayer().toString());
    }

    @Test
    public void testGameWin() {
        Game game = new Game(questionDeck, dice,"joueur1", "joueur2");
        Player currentPlayer = game.getCurrentPlayer();
        IntStream.range(0, 6).forEach(i -> currentPlayer.addReward());

        assertTrue(game.hasWinner());
    }

}

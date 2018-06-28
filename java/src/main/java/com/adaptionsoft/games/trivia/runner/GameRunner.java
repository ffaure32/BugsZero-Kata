
package com.adaptionsoft.games.trivia.runner;
import com.adaptionsoft.games.uglytrivia.Dice;
import com.adaptionsoft.games.uglytrivia.QuestionDeck;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {


	public static void main(String[] args) {
		Random rand = new Random();
		playGame(rand);
		
	}

	public static void playGame(Random rand) {
        QuestionDeck questionDeck = new QuestionDeck(50);
		Dice dice = new Dice(rand, 6);
		Game aGame = new Game(questionDeck, dice,"Chet", "Pat", "Sue");

        do {
            aGame.playRound();
		} while (!aGame.hasWinner());
	}

}

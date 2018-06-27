
package com.adaptionsoft.games.trivia.runner;
import com.adaptionsoft.games.uglytrivia.Dice;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {


	public static void main(String[] args) {
		Random rand = new Random();
		playGame(rand);
		
	}

	public static void playGame(Random rand) {
		Game aGame = new Game("Chet", "Pat", "Sue");
		Dice dice = new Dice(rand, 6);


        boolean notAWinner;
        do {
            aGame.tryToMove(dice.roll());
            if(!aGame.isCurrentPlayerInPenaltyBox()) {
                boolean wrongAnswer = simulateAnswerWrong(rand);
                if (wrongAnswer) {
                    aGame.wrongAnswer();
                } else {
                    aGame.wasCorrectlyAnswered();
                }
            }
			aGame.nextPlayer();
		} while (!aGame.hasWinner());
	}

    private static boolean simulateAnswerWrong(Random rand) {
		return rand.nextInt(9) == 7;
	}
}

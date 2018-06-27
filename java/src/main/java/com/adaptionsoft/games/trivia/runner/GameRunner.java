
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {


	public static void main(String[] args) {
		Random rand = new Random();
		playGame(rand);
		
	}

	public static void playGame(Random rand) {
		Game aGame = new Game("Chet", "Pat", "Sue");

        boolean notAWinner;
        do {
            int roll = simulateRandomRoll(rand);
            aGame.tryToMove(roll);
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

    private static int simulateRandomRoll(Random rand) {
        return rand.nextInt(5) + 1;
    }

    private static boolean simulateAnswerWrong(Random rand) {
		return rand.nextInt(9) == 7;
	}
}

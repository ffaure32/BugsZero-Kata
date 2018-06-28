package com.adaptionsoft.games.uglytrivia;

import java.util.Random;

public class Question {

    public static final int ANSWERS_COUNT = 4;

    public final String question;
    private int answerIndex = new Random().nextInt(ANSWERS_COUNT);

    public Question(String question) {
        this.question = question;
    }

    public boolean verifyAnswer(int answer) {
        return answer == answerIndex;
    }
}

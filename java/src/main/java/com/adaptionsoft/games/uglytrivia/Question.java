package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Question {

    public static final int ANSWERS_COUNT = 4;

    public final String question;
    private final List<String> answers;
    private final int answerIndex;

    public Question(String question) {
        this(question, new ArrayList<>(), new Random().nextInt(ANSWERS_COUNT));
    }

    public Question(String question, List<String> answers, int rightAnswerIndex) {
        this.question = question;
        this.answers = Collections.unmodifiableList(answers);
        this.answerIndex = rightAnswerIndex;
    }

    public boolean verifyAnswer(int answer) {
        return answer == answerIndex;
    }
}

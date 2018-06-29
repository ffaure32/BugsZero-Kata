package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class QuestionTest {
    @Test
    public void initQuestionWith4Answers() {
        List<String> answers = Arrays.asList("Earth", "Mars", "Venus", "Jupiter");
        Question question = new Question("Where do we live?", answers, 0);
        assertTrue(question.verifyAnswer(0));
        assertFalse(question.verifyAnswer(1));
    }

}
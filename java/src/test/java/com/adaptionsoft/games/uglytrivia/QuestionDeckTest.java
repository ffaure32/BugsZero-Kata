package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class QuestionDeckTest {

    @Test
    public void nextQuestionIsDifferentFromPrevious() {
        QuestionDeck deck = new QuestionDeck(2);

        Question question1 = deck.nextQuestion(Category.POP);
        Question question2 = deck.nextQuestion(Category.POP);

        assertNotSame(question1, question2);
    }

    @Test
    public void loopBackToFirstQuestionWhenDeckIsEmpty() {
        QuestionDeck deck = new QuestionDeck(2);

        Question question1 = deck.nextQuestion(Category.POP);
        Question question2 = deck.nextQuestion(Category.POP);
        Question question3 = deck.nextQuestion(Category.POP);

        assertSame(question1, question3);
    }

}
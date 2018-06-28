package com.adaptionsoft.games.trivia;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.adaptionsoft.games.uglytrivia.Player;
import com.adaptionsoft.games.uglytrivia.Question;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void rightAnswerIncreasePurse() {
        Player player = new Player("toto");

        Question question = mock(Question.class);
        when(question.verifyAnswer(anyInt())).thenReturn(true);

        player.playQuestion(question, 1);

        assertEquals(1, player.getPurse());
    }

    @Test
    public void wrongAnswerDoesntIncreasePurse() {
        Player player = new Player("toto");

        Question question = mock(Question.class);
        when(question.verifyAnswer(anyInt())).thenReturn(false);

        player.playQuestion(question, 2);

        assertEquals(0, player.getPurse());
    }


}

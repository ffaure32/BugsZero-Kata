package com.adaptionsoft.games.uglytrivia;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

public class QuestionDeck {

    private final int numberOfQuestionsByCategory;
    private Map<Category, LinkedList<Question>> questionsByCategory = new EnumMap<>(Category.class);

    public QuestionDeck(int numberOfQuestionsByCategory) {
        this.numberOfQuestionsByCategory = numberOfQuestionsByCategory;
        addQuestionsForCategories();
    }

    private void addQuestionsForCategories() {
        Arrays.stream(Category.values()).forEach(this::initQuestions);
    }

    private void initQuestions(Category category) {
        questionsByCategory.put(category, new LinkedList<>());
        for (int i = 0; i < numberOfQuestionsByCategory; i++) {
            questionsByCategory.get(category).addLast(new Question(category.toString() + " Question " + i));
        }
    }

    public Question nextQuestion(Category category) {
        LinkedList<Question> questionsForCategory = questionsByCategory.get(category);
        Question nextQuestion = questionsForCategory.removeFirst();
        questionsForCategory.addLast(nextQuestion);
        return nextQuestion;
    }

}

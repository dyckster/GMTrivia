package com.example.dombaev_yury.opentrivia.app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Question
        //TODO Убрать интерфейс когда будет база данных
        implements Serializable {

    private String category;
    private Type type;
    private Difficulty difficulty;
    private String question;
    @SerializedName("correct_answer")
    private String correctAnswer;
    @SerializedName("incorrect_answers")
    private List<String> wrongAnswers;

    private transient AnswerState answerState = AnswerState.NOT_ANSWERED;

    public void setAnswerState(AnswerState answerState) {
        this.answerState = answerState;
    }

    public AnswerState getAnswerState() {
        return answerState;
    }

    public String getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    // TODO: 03.09.17 Add shuffle method
}

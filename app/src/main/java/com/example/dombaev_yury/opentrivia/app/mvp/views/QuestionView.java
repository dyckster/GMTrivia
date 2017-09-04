package com.example.dombaev_yury.opentrivia.app.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface QuestionView extends MvpView {

    void answerQuestion(boolean correctly);

    void showAnswers(List<String> answers);

    void showQuestion(String question);
}

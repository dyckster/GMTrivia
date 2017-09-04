package com.example.dombaev_yury.opentrivia.app.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.example.dombaev_yury.opentrivia.app.model.Question;

import java.util.List;

public interface QuestionsView extends MvpView {

    void startTrivia();

    void fillWithQuestions(List<Question> questions);

    void showQuestionsLoading();

    void hideQuestionsLoading();

    void showNextQuestion(int position);

    void finishTrivia(List<Question> questions);


}

package com.example.dombaev_yury.opentrivia.app.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dombaev_yury.opentrivia.app.model.Question;
import com.example.dombaev_yury.opentrivia.app.mvp.views.QuestionView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@InjectViewState
public class QuestionPresenter extends MvpPresenter<QuestionView> {

    private Question question;

    public void setQuestion(Question question) {
        this.question = question;
        List<String> answers = new ArrayList<>(question.getWrongAnswers());
        answers.add(question.getCorrectAnswer());
        Collections.shuffle(answers);

        getViewState().showQuestion(question.getQuestion());
        getViewState().showAnswers(answers);
    }


    public boolean checkAnswer(String answer) {
        if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
            getViewState().answerQuestion(true);
            return true;
        } else {
            getViewState().answerQuestion(false);
            return false;
        }
    }
}

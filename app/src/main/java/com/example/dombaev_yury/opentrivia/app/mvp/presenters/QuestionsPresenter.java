package com.example.dombaev_yury.opentrivia.app.mvp.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dombaev_yury.opentrivia.app.api.Api;
import com.example.dombaev_yury.opentrivia.app.model.AnswerState;
import com.example.dombaev_yury.opentrivia.app.model.Difficulty;
import com.example.dombaev_yury.opentrivia.app.model.Question;
import com.example.dombaev_yury.opentrivia.app.model.ResponseModel;
import com.example.dombaev_yury.opentrivia.app.mvp.views.QuestionsView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class QuestionsPresenter extends MvpPresenter<QuestionsView> {

    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;

    private static final int DEFAULT_QUESTIONS_SIZE = 15;
    public static final long NO_CATEGORY = -1;

    public void loadQuestions(long category) {
        getViewState().showQuestionsLoading();

        if (category == NO_CATEGORY) {
            Api.getInstance().getApi().getRandomQuestions(DEFAULT_QUESTIONS_SIZE, Difficulty.EASY.toString().toLowerCase()).enqueue(callback);
        } else {
            Api.getInstance().getApi().getQuestions(DEFAULT_QUESTIONS_SIZE, (int) category).enqueue(callback);
        }
    }

    private Callback<ResponseModel<List<Question>>> callback = new Callback<ResponseModel<List<Question>>>() {
        @Override
        public void onResponse(Call<ResponseModel<List<Question>>> call, Response<ResponseModel<List<Question>>> response) {
            if (response.isSuccessful()) {
                onQuestionsLoadedSuccess(response.body().getModel());
            } else {
                onQuestionsLoadedFailure(null);
            }
        }

        @Override
        public void onFailure(Call<ResponseModel<List<Question>>> call, Throwable t) {
            onQuestionsLoadedFailure(t);

        }
    };

    private void onQuestionsLoadedSuccess(List<Question> questions) {
        this.questions = questions;
        getViewState().startTrivia();
        getViewState().hideQuestionsLoading();
        getViewState().fillWithQuestions(questions);
        getViewState().showNextQuestion(currentQuestionIndex);
    }

    private void onQuestionsLoadedFailure(@Nullable Throwable t) {
        getViewState().hideQuestionsLoading();
    }


    public void registerAnswer(boolean isCorrect) {
        questions.get(currentQuestionIndex).setAnswerState(isCorrect ? AnswerState.CORRECT : AnswerState.INCORRECT);
        if (currentQuestionIndex == questions.size() - 1) {
            getViewState().finishTrivia(questions);
        } else {
            currentQuestionIndex++;
            getViewState().showNextQuestion(currentQuestionIndex);
        }
    }

}

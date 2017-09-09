package com.example.dombaev_yury.opentrivia.app.ui.questions;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dyckster.opentrivia.R;
import com.example.dombaev_yury.opentrivia.app.model.Question;
import com.example.dombaev_yury.opentrivia.app.mvp.presenters.QuestionPresenter;
import com.example.dombaev_yury.opentrivia.app.mvp.views.QuestionView;
import com.example.dombaev_yury.opentrivia.app.ui.BaseFragment;
import com.example.dombaev_yury.opentrivia.util.AnswersView;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends BaseFragment implements QuestionView, AnswersView.OnAnswerClickListener {

    private static final String ARGUMENT_QUESTION = "question";

    @InjectPresenter
    QuestionPresenter presenter;

    public static QuestionFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_QUESTION, question);
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView questionText;
    private AnswersView answersView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_question;
    }

    @Override
    protected void initViews(View rootView) {
        answersView = rootView.findViewById(R.id.answers_view);
        questionText = rootView.findViewById(R.id.question_text);
        presenter.setQuestion((Question) getArguments().getSerializable(ARGUMENT_QUESTION));
    }


    @Override
    public void answerQuestion(boolean correctly) {
        ((QuestionsFragment) getParentFragment()).answerQuestion(correctly);
    }

    @Override
    public void showAnswers(List<String> answers) {
        answersView.setAnswers(answers, this);
    }

    @Override
    public void showQuestion(String question) {
        questionText.setText(Html.fromHtml(question));
    }


    @Override
    public boolean onAnswerClicked(String answer) {
        return presenter.checkAnswer(answer);
    }
}

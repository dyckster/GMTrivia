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

import java.util.List;

public class QuestionFragment extends BaseFragment implements QuestionView {

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

    private LinearLayout questionLayout;
    private TextView questionText;

    @Override
    protected int getLayout() {
        return R.layout.fragment_question;
    }

    @Override
    protected void initViews(View rootView) {
        questionLayout = rootView.findViewById(R.id.question_linear);
        questionText = rootView.findViewById(R.id.question_text);
        presenter.setQuestion((Question) getArguments().getSerializable(ARGUMENT_QUESTION));
    }


    @Override
    public void answerQuestion(boolean correctly) {
        ((QuestionsFragment) getParentFragment()).answerQuestion(correctly);
    }

    @Override
    public void showAnswers(List<String> answers) {
        for (String answer : answers) {
            questionLayout.addView(getTextView(answer));
        }
    }

    @Override
    public void showQuestion(String question) {
        questionText.setText(Html.fromHtml(question));
    }


    private TextView getTextView(final String answer) {
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(getActivity());
        textView.setLayoutParams(layoutParams);
        textView.setText(Html.fromHtml(answer));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(24);
        // TODO: 03.09.17 replace to dp
        textView.setPadding(0, 100, 0, 100);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 03.09.17 Проверить правильность
                presenter.checkAnswer(answer);
                //или
                //presenter.checkAnswer(((TextView) view).getText().toString());
            }
        });
        return textView;
    }
}

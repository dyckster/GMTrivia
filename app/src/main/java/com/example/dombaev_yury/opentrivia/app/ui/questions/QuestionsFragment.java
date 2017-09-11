package com.example.dombaev_yury.opentrivia.app.ui.questions;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dyckster.opentrivia.R;
import com.example.dombaev_yury.opentrivia.app.model.AnswerState;
import com.example.dombaev_yury.opentrivia.app.model.Category;
import com.example.dombaev_yury.opentrivia.app.model.Question;
import com.example.dombaev_yury.opentrivia.app.mvp.presenters.QuestionsPresenter;
import com.example.dombaev_yury.opentrivia.app.mvp.views.QuestionsView;
import com.example.dombaev_yury.opentrivia.app.ui.BaseFragment;
import com.example.dombaev_yury.opentrivia.util.AnswerMarksView;

import java.util.List;


public class QuestionsFragment extends BaseFragment implements QuestionsView {

    @InjectPresenter
    QuestionsPresenter presenter;

    private static final String ARGUMENT_CATEGORY_NAME = "category-name";
    private static final String ARGUMENT_CATEGORY_ID = "category-id";

    /**
     * Called when fragment needs to be initiated with random questions from random categories
     *
     * @return fragment instance
     */
    public static QuestionsFragment newInstance() {
        Bundle args = new Bundle();

        QuestionsFragment fragment = new QuestionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static QuestionsFragment newInstance(Category category) {
        Bundle args = new Bundle();
        args.putLong(ARGUMENT_CATEGORY_ID, category.getId());
        args.putString(ARGUMENT_CATEGORY_NAME, category.getName());
        QuestionsFragment fragment = new QuestionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private QuestionPagerAdapter adapter;
    private ViewPager questionsViewPager;
    private View questionsLoadProgress;
    private View startGroup;
    private TextView categoryText;
    private Button startButton;
    private AnswerMarksView answerMarksView;
    private static final int DELAY_BEFORE_NEXT = 700; //ms

    @Override
    protected int getLayout() {
        return R.layout.fragment_questions;
    }

    @Override
    protected void initViews(View rootView) {
        questionsViewPager = rootView.findViewById(R.id.questions_viewpager);
        questionsLoadProgress = rootView.findViewById(R.id.questions_loading_layout);
        startButton = rootView.findViewById(R.id.questions_start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadQuestions(getArguments().getLong(ARGUMENT_CATEGORY_ID, QuestionsPresenter.NO_CATEGORY));
            }
        });
        startGroup = rootView.findViewById(R.id.questions_start_group);
        categoryText = rootView.findViewById(R.id.questions_start_category);
        categoryText.setText(getArguments().getString(ARGUMENT_CATEGORY_NAME));
        answerMarksView = rootView.findViewById(R.id.answers_marks_view);
    }

    @Override
    public void startTrivia() {
        startGroup.setVisibility(View.GONE);
        questionsViewPager.setVisibility(View.VISIBLE);
    }

    @Override
    public void showQuestionsLoading() {
        questionsLoadProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideQuestionsLoading() {
        questionsLoadProgress.setVisibility(View.GONE);
    }

    @Override
    public void fillWithQuestions(List<Question> questions) {
        answerMarksView.setAnswersCount(questions.size());
        adapter = new QuestionPagerAdapter(getChildFragmentManager());
        for (Question question : questions) {
            adapter.addFragment(QuestionFragment.newInstance(question), question.getQuestion());
        }
        questionsViewPager.setAdapter(adapter);
    }


    @Override
    public void showNextQuestion(int position) {
        answerMarksView.updateNewPosition(position);
        questionsViewPager.setCurrentItem(position, true);
    }


    @Override
    public void finishTrivia(List<Question> questions) {
        int correctAnswers = 0;
        for (Question question : questions) {
            if (question.getAnswerState().equals(AnswerState.CORRECT)) {
                correctAnswers++;
            }
        }

        int incorrectAnswers = questions.size() - correctAnswers;

        Log.d("Trivia finished", "Correct answers: " + correctAnswers + "; Incorrect answers: " + incorrectAnswers);
        questionsViewPager.setVisibility(View.GONE);
    }

    public void answerQuestion(final boolean correctly) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (correctly) {
                    answerMarksView.setAnswerCorrect(questionsViewPager.getCurrentItem());
                } else {
                    answerMarksView.setAnswerIncorrect(questionsViewPager.getCurrentItem());
                }
                presenter.registerAnswer(correctly);

            }
        }, DELAY_BEFORE_NEXT);
    }

}

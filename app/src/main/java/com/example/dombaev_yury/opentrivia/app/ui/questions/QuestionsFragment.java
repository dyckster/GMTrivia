package com.example.dombaev_yury.opentrivia.app.ui.questions;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dyckster.opentrivia.R;
import com.example.dombaev_yury.opentrivia.app.model.Category;
import com.example.dombaev_yury.opentrivia.app.model.Question;
import com.example.dombaev_yury.opentrivia.app.mvp.presenters.QuestionsPresenter;
import com.example.dombaev_yury.opentrivia.app.mvp.views.QuestionsView;
import com.example.dombaev_yury.opentrivia.app.ui.BaseFragment;

import java.util.List;


public class QuestionsFragment extends BaseFragment implements QuestionsView {

    @InjectPresenter
    QuestionsPresenter presenter;

    private static final String ARGUMENT_CATEGORY_NAME = "category-name";
    private static final String ARGUMENT_CATEGORY_ID = "category-id";

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
                presenter.loadQuestions(getArguments().getLong(ARGUMENT_CATEGORY_ID));
            }
        });
        startGroup = rootView.findViewById(R.id.questions_start_group);
        categoryText = rootView.findViewById(R.id.questions_start_category);

        categoryText.setText(getArguments().getString(ARGUMENT_CATEGORY_NAME));


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
        adapter = new QuestionPagerAdapter(getChildFragmentManager());
        for (Question question : questions) {
            adapter.addFragment(QuestionFragment.newInstance(question), question.getQuestion());
        }
        questionsViewPager.setAdapter(adapter);
    }


    @Override
    public void showNextQuestion(int position) {
        questionsViewPager.setCurrentItem(position, true);
    }


    @Override
    public void finishTrivia(List<Question> questions) {

    }

    public void answerQuestion(boolean correctly) {
        Toast.makeText(getActivity(), "Answer is correct: " + correctly, Toast.LENGTH_SHORT).show();
        presenter.registerAnswer(correctly);
    }

}

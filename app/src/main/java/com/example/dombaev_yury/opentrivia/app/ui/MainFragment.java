package com.example.dombaev_yury.opentrivia.app.ui;

import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dyckster.opentrivia.R;
import com.example.dombaev_yury.opentrivia.app.mvp.presenters.MainPresenter;
import com.example.dombaev_yury.opentrivia.app.mvp.views.MainView;
import com.example.dombaev_yury.opentrivia.app.ui.categories.CategoriesFragment;
import com.example.dombaev_yury.opentrivia.app.ui.questions.QuestionsFragment;

public class MainFragment extends BaseFragment implements MainView {

    @InjectPresenter
    MainPresenter mainPresenter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initViews(View rootView) {
        Button playButton = rootView.findViewById(R.id.button_play);
        Button categoriesButton = rootView.findViewById(R.id.button_categories);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playGame();
            }
        });

        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCategories();
            }
        });
    }

    @Override
    public void playGame() {
        ((MainActivity) getActivity()).setCurrentFragment(QuestionsFragment.newInstance());
    }

    @Override
    public void openCategories() {
        ((MainActivity) getActivity()).setCurrentFragment(new CategoriesFragment());

    }
}

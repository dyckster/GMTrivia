package com.example.dombaev_yury.opentrivia.app.ui.categories;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dyckster.opentrivia.R;
import com.example.dombaev_yury.opentrivia.app.model.Category;
import com.example.dombaev_yury.opentrivia.app.mvp.presenters.CategoriesPresenter;
import com.example.dombaev_yury.opentrivia.app.mvp.views.CategoriesView;
import com.example.dombaev_yury.opentrivia.app.ui.BaseActivity;
import com.example.dombaev_yury.opentrivia.app.ui.BaseFragment;
import com.example.dombaev_yury.opentrivia.app.ui.MainActivity;
import com.example.dombaev_yury.opentrivia.app.ui.questions.QuestionsFragment;

import java.util.List;

public class CategoriesFragment extends BaseFragment implements CategoriesView, CategoryClickListener {

    @InjectPresenter
    CategoriesPresenter categoriesPresenter;

    private View progressView;
    private RecyclerView categoriesRecycler;
    private CategoriesAdapter adapter = new CategoriesAdapter();

    @Override
    protected int getLayout() {
        return R.layout.fragment_categories;
    }

    @Override
    protected void initViews(View rootView) {
        categoriesRecycler = rootView.findViewById(R.id.categories_recycler);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoriesRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        categoriesRecycler.setAdapter(adapter);
        adapter.setOnClickListener(this);

        progressView = rootView.findViewById(R.id.categories_loading_layout);

        categoriesPresenter.loadCategories();
    }


    @Override
    public void showCategories(List<Category> categories) {
        adapter.setItems(categories);
    }

    @Override
    public void showCategoriesLoadingProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCategoriesLoadingProgress() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void openCategoryScreen(Category category) {
        ((MainActivity) getActivity()).setCurrentFragment(QuestionsFragment.newInstance(category));
    }

    @Override
    public void showNoNetworkView() {
// TODO: 03.09.17  
    }

    @Override
    public void hideNoNetworkView() {
// TODO: 03.09.17  
    }

    @Override
    public void onClick(int position) {
        categoriesPresenter.onCategoryClicked(position);
    }
}

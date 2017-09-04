package com.example.dombaev_yury.opentrivia.app.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.dombaev_yury.opentrivia.app.model.Category;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CategoriesView extends MvpView {

    void showCategories(List<Category> categories);

    void showCategoriesLoadingProgress();

    void hideCategoriesLoadingProgress();

    void openCategoryScreen(Category category);

    void showNoNetworkView();

    void hideNoNetworkView();
}

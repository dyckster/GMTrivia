package com.example.dombaev_yury.opentrivia.app.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.dombaev_yury.opentrivia.app.model.Category;

import java.util.List;

public interface CategoriesView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCategories(List<Category> categories);

    @StateStrategyType(SkipStrategy.class)
    void showCategoriesLoadingProgress();

    void hideCategoriesLoadingProgress();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openCategoryScreen(Category category);

    void showNoNetworkView();

    void hideNoNetworkView();
}

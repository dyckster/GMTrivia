package com.example.dombaev_yury.opentrivia.app.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dombaev_yury.opentrivia.app.api.Api;
import com.example.dombaev_yury.opentrivia.app.model.Category;
import com.example.dombaev_yury.opentrivia.app.model.ResponseModel;
import com.example.dombaev_yury.opentrivia.app.mvp.views.CategoriesView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class CategoriesPresenter extends MvpPresenter<CategoriesView> {


    private List<Category> categories = new ArrayList<>();

    public void loadCategories() {
        getViewState().showCategoriesLoadingProgress();
        Api.getInstance().getApi().getCategories().enqueue(new Callback<ResponseModel<List<Category>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<Category>>> call, Response<ResponseModel<List<Category>>> response) {
                onLoadSuccess(response.body().getModel());
            }

            @Override
            public void onFailure(Call<ResponseModel<List<Category>>> call, Throwable t) {
                onLoadError(t);
            }
        });
    }

    private void onLoadSuccess(List<Category> categories) {
        this.categories = categories;

        getViewState().showCategories(categories);
        getViewState().hideCategoriesLoadingProgress();
    }

    private void onLoadError(Throwable t) {
        getViewState().hideCategoriesLoadingProgress();

    }


    public void retryLoading() {
        loadCategories();

    }

    public void onCategoryClicked(int position) {
        getViewState().openCategoryScreen(categories.get(position));
    }
}

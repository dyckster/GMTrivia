package com.example.dombaev_yury.opentrivia.app.api;

import com.example.dombaev_yury.opentrivia.app.model.Category;
import com.example.dombaev_yury.opentrivia.app.model.Question;
import com.example.dombaev_yury.opentrivia.app.model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaService {

    @GET("api.php")
    Call<ResponseModel<List<Question>>> getQuestions(@Query("amount") int amount, @Query("category") int category);

    @GET("api_category.php")
    Call<ResponseModel<List<Category>>> getCategories();
}

package com.example.dombaev_yury.opentrivia.app.api;

import com.dyckster.opentrivia.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {
    private static final Api ourInstance = new Api();

    public static Api getInstance() {
        return ourInstance;
    }

    private final TriviaService triviaService;

    private Api() {
        triviaService = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(TriviaService.class);
    }

    public TriviaService getApi() {
        return triviaService;
    }
}

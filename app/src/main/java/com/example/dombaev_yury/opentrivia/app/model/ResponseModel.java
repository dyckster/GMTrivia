package com.example.dombaev_yury.opentrivia.app.model;


import com.google.gson.annotations.SerializedName;

public class ResponseModel<T> {

    @SerializedName("response_code")
    private int responseCode;

    @SerializedName(value = "results", alternate = "trivia_categories")
    private T model;

    public T getModel() {
        return model;
    }

    public int getResponseCode() {
        return responseCode;
    }
}

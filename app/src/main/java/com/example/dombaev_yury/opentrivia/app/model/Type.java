package com.example.dombaev_yury.opentrivia.app.model;

import com.google.gson.annotations.SerializedName;

public enum Type {
    //TODO Уточнить, нужно ли тут Serialized name? поля отличаются просто регистром
    @SerializedName("boolean")
    BOOLEAN,
    @SerializedName("multiple")
    MULTIPLE
}

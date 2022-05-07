package com.example.android.cats;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public final class Cat implements Serializable {
    @SerializedName("description")
    private final String description;
    @SerializedName("image")
    private final CatImage image;
    @SerializedName("name")
    private final String name;
    @SerializedName("country_code")
    private final String country_code;
    @SerializedName("temperament")
    private final String temperament;
    @SerializedName("wikipedia_url")
    private final String wikipedia_url;

    public Cat(String description, CatImage image, String name, String country_code, String temperament, String wikipedia_url) {
        this.description = description;
        this.image = image;
        this.name = name;
        this.country_code = country_code;
        this.temperament = temperament;
        this.wikipedia_url = wikipedia_url;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public String getDescription() {
        return description;
    }

    public CatImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}



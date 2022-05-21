package com.example.android.cats;

import java.io.Serializable;

public final class Cat implements Serializable {
    private final String description;
    private final CatImage image;
    private final String name;
    private final String country_code;
    private final String temperament;
    private final String wikipedia_url;
    private final String origin;

    public Cat(String description, CatImage image, String name, String country_code, String temperament, String wikipedia_url, String origin) {
        this.description = description;
        this.image = image;
        this.name = name;
        this.country_code = country_code;
        this.temperament = temperament;
        this.wikipedia_url = wikipedia_url;
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
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



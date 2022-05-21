package com.example.android.cats;

import java.io.Serializable;

public class CatImage implements Serializable {
    private final String id;
    private final String url;

    public CatImage(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}

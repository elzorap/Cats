package com.example.android.cats;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CatImage implements Serializable {
    @SerializedName("id")
    private final String id;
    @SerializedName("width")
    private final int width;
    @SerializedName("height")
    private final int height;
    @SerializedName("url")
    private final String url;

    public CatImage(String id, int width, int height, String url) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }
}

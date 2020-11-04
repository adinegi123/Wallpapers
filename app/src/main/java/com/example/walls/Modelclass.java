package com.example.walls;

public class Modelclass {
    private String url;
    private int likes;
    public Modelclass(String url, int likes)
    {
        this.url=url;
        this.likes=likes;
    }

    public String getUrl() {
        return url;
    }

    public int getLikes() {
        return likes;
    }
}

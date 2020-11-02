package com.example.testapp2019sliit;

public class Comments {
    private String game_name;
    private String comment;
    private int ratings;

    public Comments(String game_name, String comment, int ratings) {
        this.game_name = game_name;
        this.comment = comment;
        this.ratings = ratings;
    }

    public Comments() {
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

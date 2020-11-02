package com.example.testapp2019sliit;

public class Game {
    private String game_name;
    private int year;

    public Game(String game_name, int year) {
        this.game_name = game_name;
        this.year = year;
    }

    public Game() {
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

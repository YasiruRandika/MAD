package com.example.testapp2019sliit;

public class DatabaseMaster {
    public static class Users {
        public static final String USERS_TABLE_NAME = "USERS";
        public static final String USERS_USERNAME = "USERNAME";
        public static final String USERS_PASSWORD = "PASSWORD";
        public static final String USERS_USER_TYPE = "USER_TYPE";

        private String userName, password;

        public Users() {
        }

        public Users(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public String createTable() {
            return "CREATE TABLE " + USERS_TABLE_NAME + " (" + USERS_USERNAME + " TEXT PRIMARY KEY, " + USERS_PASSWORD + " TEXT, " + USERS_USER_TYPE + " TEXT)";
        }
    }

    public static class Game {
        public static final String GAMES_TABLE_NAME = "GAMES";
        public static final String GAMES_NAME = "NAME";
        public static final String GAMES_YEAR = "YEAR";

        private String name, year;

        public Game() {
        }

        public Game(String name, String year) {
            this.name = name;
            this.year = year;
        }

        public String createTable() {
            return "CREATE TABLE " + GAMES_TABLE_NAME + " (" + GAMES_NAME + " TEXT PRIMARY KEY, " + GAMES_YEAR + " INTEGER)";
        }
    }

    public static class Comments {
        public static final String COMMENTS_TABLE_NAME = "COMMENTS";
        public static final String COMMENTS_GAME_NAME = "GAME_NAME";
        public static final String COMMENTS_RATINGS = "RATINGS";
        public static final String COMMENTS_COMMENT = "COMMENT";

        private String game, ratings, comment;

        public Comments() {
        }

        public Comments(String game, String ratings, String comment) {
            this.game = game;
            this.ratings = ratings;
            this.comment = comment;
        }

        public String createTable() {
            return "CREATE TABLE " + COMMENTS_TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT," + COMMENTS_GAME_NAME + " TEXT, " + COMMENTS_RATINGS + " INTEGER, " + COMMENTS_COMMENT + " TEXT, FOREIGN KEY(GAME_NAME) REFERENCES GAME(NAME))";
        }
    }
}

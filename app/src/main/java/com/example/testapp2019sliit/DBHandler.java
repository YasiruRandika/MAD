package com.example.testapp2019sliit;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private Context context;
    public DBHandler(@Nullable Context context) {
        super(context, "my_game.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DatabaseMaster.Users users = new DatabaseMaster.Users();
        DatabaseMaster.Comments comments = new DatabaseMaster.Comments();
        DatabaseMaster.Game game = new DatabaseMaster.Game();

        db.execSQL(users.createTable());
        db.execSQL(game.createTable());
        db.execSQL(comments.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE COMMENT");
        db.execSQL("DROP TABLE GAME");
        db.execSQL("DROP TABLE USERS");
        onCreate(db);
    }

    public boolean registerUser(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", userName);
        contentValues.put("PASSWORD", password);

        long status = db.insert("USERS", null, contentValues);

        db.close();

        if (status == -1) {
            Log.d("DB", "User Registered Failed");
            return false;
        } else {
            Log.d("DB", "User Registered Successfully");
            return true;
        }
    }

    public boolean addGame(String gameName, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", gameName);
        contentValues.put("YEAR", year);

        long status = db.insert("GAMES", null, contentValues);

        db.close();

        if (status == -1) {
            Log.d("DB", "Game Insert Failed");
            return false;
        } else {
            Log.d("DB", "Game Inserted Successfully");
            return true;
        }
    }

    public boolean addComment(Comments comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COMMENT", comment.getComment());
        contentValues.put("RATINGS", comment.getRatings());
        contentValues.put("GAME_NAME", comment.getGame_name());

        long status = db.insert("COMMENTS", null, contentValues);

        db.close();

        if (status == -1) {
            Log.d("DB", "Comment Insert Failed");
            return false;
        } else {
            Log.d("DB", "Comment Inserted Successfully");
            return true;
        }
    }

    public int login(String userName, String password) {
        int status = 0;

        String db_password = null;

        SQLiteDatabase db = this.getWritableDatabase();

        String sqlQuery = "SELECT * FROM USERS WHERE USERNAME = '" + userName + "'";

        Cursor cursor = db.rawQuery(sqlQuery, null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            db_password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
            cursor.moveToNext();
        }

        if (db_password != null) {
            Log.d("DB", "Password Not Equals Null");
            if (db_password.equals(password)) {
                Log.d("DB", "PASSWORD MATCHES");
                if (userName.equals("admin")) {
                    Log.d("DB", "USER ADMIN");
                    status = 1;
                } else {
                    Log.d("DB", "USER NORMAL");
                    status = 2;
                }
            }
            else {
                Toast.makeText(context, "Login Failed ! Invalid Password", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Login Failed ! Invalid User Name", Toast.LENGTH_LONG).show();
        }

        return status;
    }

    public ArrayList<Comments> getComments(String gameName) {
       ArrayList<Comments> arrayList = new ArrayList<>();

        int status = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        String sqlQuery = "SELECT * FROM COMMENTS WHERE GAME_NAME = '" + gameName + "'";

        Cursor cursor = db.rawQuery(sqlQuery, null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Comments comments = new Comments();
            comments.setComment(cursor.getString(cursor.getColumnIndex("COMMENT")));
            comments.setRatings(cursor.getInt(cursor.getColumnIndex("RATINGS")));
            arrayList.add(comments);
            cursor.moveToNext();
        }

        db.close();

       return arrayList;
    }

    public ArrayList<Game> getGameList() {
        ArrayList<Game> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sqlQuery = "SELECT * FROM GAMES";

        Cursor cursor = db.rawQuery(sqlQuery, null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Game game = new Game();
            game.setGame_name(cursor.getString(cursor.getColumnIndex("NAME")));
            game.setYear(cursor.getInt(cursor.getColumnIndex("YEAR")));

            arrayList.add(game);
            cursor.moveToNext();
        }

        db.close();

        return arrayList;
    }
}

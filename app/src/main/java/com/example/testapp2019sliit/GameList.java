package com.example.testapp2019sliit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class GameList extends AppCompatActivity implements GameListAdapter.gameListOnClick {
    private ArrayList<Game> gameArrayList;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        dbHandler = new DBHandler(this);

        gameArrayList = dbHandler.getGameList();

        Log.d("DB", "Array Size " + String.valueOf(gameArrayList.size()));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GameListAdapter gameListAdapter = new GameListAdapter(gameArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(gameListAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void gameListItemClick(String game) {
        Intent intent = new Intent(this, GameOverview.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }
}
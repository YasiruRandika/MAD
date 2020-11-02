package com.example.testapp2019sliit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddGame extends AppCompatActivity {
    private TextView game, year;
    private Button addGame;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        game = findViewById(R.id.gameName);
        year = findViewById(R.id.yearD);
        addGame = findViewById(R.id.addGame);
        dbHandler = new DBHandler(this);

        addGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = dbHandler.addGame(game.getText().toString(), Integer.parseInt(year.getText().toString()));

                if (status) {
                    game.setText("");
                    year.setText("");
                    Toast.makeText(AddGame.this, "Successfully Added", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddGame.this, "Adding Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
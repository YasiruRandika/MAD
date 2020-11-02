package com.example.testapp2019sliit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameOverview extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView comment, currentRatings;
    private Button button;
    private int rating;
    private String gameName;
    private ArrayList<Comments> commentsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overview);
        gameName = getIntent().getStringExtra("game");

        DBHandler dbHandler = new DBHandler(getApplicationContext());
        commentsArrayList = dbHandler.getComments(gameName);

        Log.d("DB", "ARRAY LIST SIZE " + gameName + " " + String.valueOf(commentsArrayList.size()));

        int total = 0;
        int count = 0;

        String[] commentsString = new String[commentsArrayList.size()];

        for (Comments comment : commentsArrayList) {
            commentsString[count] = comment.getComment();
            total = total + comment.getRatings();
            count++;
        }

        Log.d("DB", commentsString[0]);



        double average = 0.0;

        try {
            average = total / count;
        } catch (Exception e) {
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commentsString);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);



        seekBar = findViewById(R.id.seekBar);
        comment = findViewById(R.id.comment);
        button = findViewById(R.id.submit);
        currentRatings = findViewById(R.id.currentRatings);

        currentRatings.setText(String.valueOf(average));

        rating = 0;


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               rating = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment.getText().toString().length() != 0) {
                    Comments comments = new Comments(gameName, comment.getText().toString(), rating);
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    boolean status = dbHandler.addComment(comments);

                    if (status) {
                        comment.setText("");
                        seekBar.setProgress(0);
                        Toast.makeText(getApplicationContext(), "Comment Added Successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Comment Adding Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
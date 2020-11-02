package com.example.testapp2019sliit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView userName, password;
    Button login, register;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.register);

        dbHandler = new DBHandler(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = dbHandler.registerUser(userName.getText().toString(), password.getText().toString());

                if (status) {
                    userName.setText("");
                    password.setText("");

                    Toast.makeText(MainActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = dbHandler.login(userName.getText().toString(), password.getText().toString());
                Intent intent;

                switch (status){
                    case 1:
                        Log.d("DB", "USER ADMIN RETURN");
                        intent = new Intent(MainActivity.this, AddGame.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        Log.d("DB", "USER NORMAL RETURN");
                        intent = new Intent(MainActivity.this, GameList.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }
}
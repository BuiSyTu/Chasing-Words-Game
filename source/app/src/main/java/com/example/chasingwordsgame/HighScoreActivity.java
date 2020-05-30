package com.example.chasingwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HighScoreActivity extends AppCompatActivity {

    Database database;
    TextView txtHighScore;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        database = new Database(this, "dhbc.sqlite", null, 1);
        Cursor cusor = database.GetData("Select * from Result order by score desc Limit 5");
        String result = "";
        int count = 1;
        while (cusor.moveToNext()) {
            Integer score = cusor.getInt(2);
            String name = cusor.getString(1);
            result += count + "." + name + ":   " + score + "\n";
            count++;
        }

        txtHighScore = findViewById(R.id.textViewHighScore);
        txtHighScore.setText(result);

        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

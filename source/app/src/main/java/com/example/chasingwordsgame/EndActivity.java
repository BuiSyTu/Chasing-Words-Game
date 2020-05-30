package com.example.chasingwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    Button btnBack;
    TextView txtCountCorrect, txtCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        TextView txtCountCorrect = findViewById(R.id.textViewCountCorrect);
        TextView txtCoin = findViewById(R.id.textViewCoin);


        Intent intent = getIntent();
        int coin = intent.getIntExtra("coin", 0);
        int countExtra = intent.getIntExtra("countCorrect", 0);

        txtCoin.setText("Vàng:              " + coin);
        txtCountCorrect.setText("Điểm:              " + countExtra);


        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

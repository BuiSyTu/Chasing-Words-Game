package com.example.chasingwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtCoin;
    private TextView txtHeart;
    private TextView txtResult;
    private LinearLayout layout5;
    private LinearLayout layout4;
    private LinearLayout layoutResult;
    private LinearLayout layoutImage;
    private LinearLayout layoutButton;
    private Button btnChoose;

    private int coin = 0;
    private int heart = 1;
    private int i = 0;
    private int dem = 0;
    private  boolean isCorrect = false;

    private final String[] DAP_AN ={
            "HOIDONG",
            "AOMUA",
            "BAOCAO",
            "OTO",
            "DANONG",
            "CANTHIEP",
            "CATTUONG",
            "DANHLUA",
            "TICHPHAN",
            "QUYHANG",
            "GIANGMAI",
            "GIANDIEP",
            "SONGSONG",
            "THOTHE",
            "THATTINH",
            "MASAT",
            "HONGTAM"
    };
    public static final int[] QUESTIONS={
            R.drawable.hoidong,
            R.drawable.aomua,
            R.drawable.baocao,
            R.drawable.oto,
            R.drawable.danong,
            R.drawable.canthiep,
            R.drawable.cattuong,
            R.drawable.danhlua,
            R.drawable.tichphan,
            R.drawable.quyhang,
            R.drawable.giangmai,
            R.drawable.giandiep,
            R.drawable.songsong,
            R.drawable.thothe,
            R.drawable.thattinh,
            R.drawable.masat,
            R.drawable.hongtam,
    };

    private Random random = new Random();
    private String result = "";
    private Button[] btnResults;
    private int rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initText();
        rd = random();
        createButton();
        createImage();
        createButtonPick();
    }

    public void initText() {
        txtCoin= (TextView) findViewById(R.id.txt_coin);
        txtHeart = (TextView) findViewById(R.id.txt_avatar);
        txtResult = (TextView) findViewById(R.id.txt_ketQua);
    }

    public boolean check(ArrayList<Integer> numbers, int n){
        for (int i = 0; i < numbers.size(); i++){
            if (n == numbers.get(i)){
                return false;
            }
        }
        return true;
    }

    public int random() {
        ArrayList<Integer> rds = new ArrayList<>();
        int rdNumber = 0;
        while (check(rds, rdNumber)) {
            rdNumber = random.nextInt(QUESTIONS.length);
            rds.add(rdNumber);
        }
        return rdNumber;
    }

    public ArrayList randomQuestions() {
        ArrayList<String> results = new ArrayList<>();
        int tm = random.nextInt(25) + 65;
        for (int i = 0; i < DAP_AN[rd].length(); i++) {
            results.add(DAP_AN[rd].charAt(i) + "");
        }

        for(int i = 0; i < 16 - DAP_AN[rd].length(); i++) {
            results.add((char)tm + "");
        }
        return results;
    }

    public void createButton() {
        layoutResult = (LinearLayout) findViewById(R.id.layout_3);
        btnResults = new Button[DAP_AN[rd].length()];
        int i = 0;
        for (i = 0; i < DAP_AN[rd].length(); i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(120, 120, 1));
            btn.setId(i);
            btn.setBackgroundResource(R.drawable.button_xam);
            layoutResult.addView(btn);
            btnResults[i] = (Button) findViewById(btn.getId());
        }
    }

    public void createImage() {
        layoutImage = (LinearLayout) findViewById(R.id.layout_2);
        ImageView[]iv = new ImageView[QUESTIONS.length];
        iv[rd] = new ImageView(this);
        iv[rd].setImageResource(QUESTIONS[rd]);
        layoutImage.addView(iv[rd]);
    }

    public void createButtonPick(){
        layout4 = (LinearLayout) findViewById(R.id.layout_4);
        layout5 = (LinearLayout) findViewById(R.id.layout_5);
        ArrayList<Integer> arrSo = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            Button btn= new Button(this);

            btn.setLayoutParams(new LinearLayout.LayoutParams(100, 110, 1));
            btn.setBackgroundResource(R.drawable.tile_hover);

            btn.setOnClickListener(this);
            while (btn.getText()=="") {
                int tmp = random.nextInt(16);
                if (check(arrSo, tmp)) {
                    btn.setText((CharSequence) randomQuestions().get(tmp));
                    randomQuestions().remove(tmp);
                    arrSo.add(tmp);
                }
            }
            layout4.addView(btn);
        }
        for (int i = 0; i < 8; i++){
            Button btn= new Button(this);

            btn.setLayoutParams(new LinearLayout.LayoutParams(100, 110, 1));
            btn.setBackgroundResource(R.drawable.tile_hover);

            btn.setOnClickListener(this);
            while (btn.getText()=="") {
                int tmp = random.nextInt(16);
                if (check(arrSo, tmp)) {
                    btn.setText((CharSequence) randomQuestions().get(tmp));
                    randomQuestions().remove(tmp);
                    arrSo.add(tmp);
                }
            }
            layout5.addView(btn);
        }
    }

    @SuppressLint("ResourceType")
    public void createButtonChoose(){
        btnChoose=new Button(this);
        layoutButton= (LinearLayout) findViewById(R.id.layout_buton);
        btnChoose.setLayoutParams(new LinearLayout.LayoutParams(450,150));
        btnChoose.setBackgroundResource(R.drawable.next);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCorrect) {
                    coin += 100;
                    txtCoin.setText(coin + "");
                    layout4.removeAllViews();
                    layout5.removeAllViews();
                    layoutResult.removeAllViews();
                    layoutImage.removeAllViews();
                    layoutButton.removeAllViews();
                    rd = random();
                    createButtonPick();
                    createImage();
                    createButton();
                    txtResult.setText("");
                    result="";
                    dem=0;
                    i=0;
                } else {
                    heart -= 1;
                    txtHeart.setText(heart + "");
                    layout4.removeAllViews();
                    layout5.removeAllViews();
                    layoutResult.removeAllViews();
                    layoutImage.removeAllViews();
                    layoutButton.removeAllViews();
                    createButtonPick();
                    createImage();
                    createButton();
                    txtResult.setText("");
                    result="";
                    dem=0;
                    i=0;
                }
            }
        });
        layoutButton.addView(btnChoose);
    }

    @Override
    public void onClick(View v) {
        checkHeart();
        Button button = (Button) v;
        btnResults[i].setText(button.getText());
        result += button.getText();
        v.setEnabled(false);
        v.setBackgroundColor(601800);
        ((Button) v).setText("");
        dem++;

        if (dem == DAP_AN[rd].length()) {
            if (result.equals(DAP_AN[rd])) {
                for (int i = 0; i < DAP_AN[rd].length(); i++) {
                    btnResults[i].setBackgroundResource(R.drawable.tile_true);
                }
                txtResult.setText("Bạn đã trả lời đúng !!!");
                isCorrect = true;
                createButtonChoose();
                btnChoose.setText("NEXT");
                layoutButton.setVisibility(View.VISIBLE);
                return;
            } else {
                for (int i = 0; i < DAP_AN[rd].length(); i++) {
                    btnResults[i].setBackgroundResource(R.drawable.tile_false);
                }
                txtResult.setText("Bạn đã trả lời sai !!!");
                isCorrect = false;
                createButtonChoose();
                btnChoose.setText("AGAIN");
                layoutButton.setVisibility(View.VISIBLE);
                return;
            }
        }

        i++;
    }

    public void checkHeart() {
        if (heart == 0){
            Intent intent = new Intent(PlayActivity.this, EndActivity.class);
            startActivity(intent);
        }
    }

}
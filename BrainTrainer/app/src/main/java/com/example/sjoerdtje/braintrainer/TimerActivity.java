package com.example.sjoerdtje.braintrainer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class TimerActivity extends AppCompatActivity {
    private TextView timerText;
    private TextView scoreText;
    private Button[] button;
    private int goedAntwoord;
    private int aantalGoed;
    private int aantalBeurten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerText = (TextView) findViewById(R.id.timerText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        button = new Button[4];
        for (int i = 0; i < button.length; i++) {
            button[i] = (Button) gridLayout.getChildAt(i);

        }
        startTimer();
        maakSom();

    }

    private void startTimer() {
        new CountDownTimer(30000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int seconden = (int) millisUntilFinished / 1000;
                timerText.setText(String.valueOf(seconden));

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("score",aantalGoed);
                startActivity(intent);

            }
        }.start();
    }

    private void maakSom() {
        Random randomizer = new Random();
        int getal1 = randomizer.nextInt(50) + 1;
        int getal2 = randomizer.nextInt(50) + 1;
        TextView somText = (TextView) findViewById(R.id.somText);
        somText.setText(String.valueOf(getal1) + "+" + getal2);
        goedAntwoord = getal1 + getal2;

        int goedAntwoordButton = randomizer.nextInt(4);
        button[goedAntwoordButton].setText(String.valueOf(goedAntwoord));
        for (int i = 0; i < button.length; i++) {
            if (i != goedAntwoordButton) {
                int foutAntwoord = randomizer.nextInt(50) + 1;
                while (foutAntwoord == goedAntwoord) {
                    foutAntwoord = randomizer.nextInt(50) + 1;
                }
                button[i].setText(String.valueOf(foutAntwoord));
            }
        }

    }

    public void checkUitkomst(View view) {
        aantalBeurten++;
        Button knop = (Button) view;
        int antwoord = Integer.parseInt(knop.getText().toString());
        if (antwoord == goedAntwoord) {
            aantalGoed++;

        }
        scoreText.setText(String.valueOf(aantalGoed)+ "/" + aantalBeurten);
        maakSom();
    }
}

package com.example.sjoerdtje.eierkoker;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView countdownTextView;
    Button button;
    SeekBar seekBar;
    int timerTijd;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        timerTijd = 30;

        countdownTextView = (TextView)findViewById(R.id.countdownTextView);
        button = (Button)findViewById(R.id.button);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(timerTijd);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser){
                timerTijd = progress;
                setTimerText();
            }

            public void onStartTrackingTouch(SeekBar seekBar){

            }

            public void onStopTrackingTouch(SeekBar seekBar){

            }
        });
    }

            private void setTimerText(){
                int minuten = timerTijd /60;
                int seconden = timerTijd - minuten * 60;
                String tijdString = minuten + ":" + seconden;
                if(seconden < 10){
                    tijdString = minuten + ":0" + seconden;
                }
                countdownTextView.setText(tijdString);
            }

    public void startStop(View view){
        if (timer != null){
            timer.cancel();
            button.setText("Start");
            timer=null;
        }else {
            button.setText("Cancel");


            timer = new CountDownTimer(timerTijd * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTijd = (int) millisUntilFinished / 1000;
                    setTimerText();
                }

                @Override
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.luchthoorn);
                    mediaPlayer.start();
                    countdownTextView.setText("0:00");

                }
            };
            timer.start();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

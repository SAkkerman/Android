package com.example.sjoerdtje.braintrainer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView highScores;
    TextView newHighScores;
    int highScore;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = this.getPreferences(Context.MODE_PRIVATE);
        highScore = prefs.getInt("highScore", 0);

        highScores = (TextView)findViewById(R.id.highScores);
        newHighScores = (TextView) findViewById(R.id.newHighScore);

        Intent intent=getIntent();
        int score = intent.getIntExtra("score",0);

        if(score > highScore){
            highScore = score;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore",highScore);
            editor.commit();
            newHighScores.setText("New High Score");
        }

        highScores.setText("highScore: " + highScore);

    }

    public void start(View view){
        Intent intent = new Intent(this,TimerActivity.class);
        startActivity(intent);

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

        if (id == R.id.action_reset) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore", 0);
            editor.commit();
            highScores.setText("highScore: " + 0);
        }


        return super.onOptionsItemSelected(item);
    }
}

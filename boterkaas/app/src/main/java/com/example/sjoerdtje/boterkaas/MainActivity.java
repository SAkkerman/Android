package com.example.sjoerdtje.boterkaas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int spelerActief;
    int [] spelverloop={0,0,0,0,0,0,0,0,0};
    int [] [] winners = {
        {0,1,2},
        {3,4,5},
        {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {3,4,6},
    };
    int spelergewonnen;
    boolean spelafloop;
    TextView eindespel;
    Button Reset;
    LinearLayout gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spelerActief =1;
        spelergewonnen = 0;
        eindespel =(TextView)findViewById(R.id.eindespel);
        Reset =(Button)findViewById(R.id.Reset);
        gameOver = (LinearLayout)findViewById(R.id.gameOver);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void setImg(View view) {
        ImageView imageview = (ImageView) view;
        int index = Integer.parseInt(imageview.getTag().toString());

        if (spelverloop[index] == 0) {
            spelverloop[index] = spelerActief;

            if (spelerActief == 1) {
                imageview.setTranslationY(-1000f);
                imageview.setImageResource(R.drawable.kruisje);
                imageview.animate().translationY(0f).setDuration(300);
                checkEindeSpel();
                spelerActief = 2;
            } else {
                imageview.setTranslationY(-1000f);
                imageview.setImageResource(R.drawable.rondje);
                imageview.animate().translationY(0f).setDuration(300);
                checkEindeSpel();
                spelerActief = 1;
            }
            if (spelafloop) {
                System.out.println("Check");
                eindigSpel();
            }
        }
    }

    private void checkEindeSpel(){
        for(int[]winner:winners){
            boolean isWinner =true;
            for(int i:winner) {
                if (spelverloop[i] !=spelerActief ) {
                    isWinner = false;
                    break;
                }
            }
            if(isWinner){
                spelergewonnen = spelerActief;
                spelafloop = true;
                System.out.println("Speler gewonnen: " + spelergewonnen);
            }
        }
        //Situatie gelijkspel
        if(spelergewonnen == 0){
            spelafloop = true;
            for(int i:spelverloop){
                if(i==0){
                    spelafloop = false;
                    break;
                }
            }

        }

    }

    private void eindigSpel(){
        switch(spelergewonnen){
            case 0:
                eindespel.setText("Gelijkspel, Opnieuw starten?");
                break;
            case 1:
                eindespel.setText("Speler 1 heeft gewonnen, Opnieuw starten?");
                break;
            case 2:
                eindespel.setText("Speler 2 heeft gewonnen, Opnieuw starten?");
                break;

        }
        gameOver.setVisibility(View.VISIBLE);

    }

    public void newGame(View view){
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < spelverloop.length;i++){
            spelverloop[i] = 0;
            ImageView imageView = (ImageView)gridLayout.getChildAt(i);
            imageView.setImageResource(0);
        }
        spelerActief =1;
        spelafloop = false;
        spelergewonnen=0;
        gameOver.setVisibility(View.GONE);
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

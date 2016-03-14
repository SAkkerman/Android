package com.example.sjoerdtje.animatie;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ImageView rich;
    ImageView really;
    boolean evolve;
    long duurAnimatie;
    SeekBar seekBar;
    RadioButton fadeButton;
    RadioButton translateButton;
    RadioButton rotateButton;
    boolean isFade;
    boolean isTranslate;
    boolean isRotate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rich = (ImageView) findViewById(R.id.rich);
        really = (ImageView) findViewById(R.id.really);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                duurAnimatie = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        fadeButton = (RadioButton) findViewById(R.id.fadeRadioButton);
        fadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFade = true;
                isTranslate = false;
                isRotate = false;

                if(evolve){
                    really.setAlpha(0f);
                    really.setTranslationX(0f);
                    really.setScaleX(1f);
                    really.setScaleY(1f);
                }
                else {
                    rich.setAlpha(0f);
                    rich.setTranslationX(0f);
                    rich.setScaleX(1f);
                    rich.setScaleY(1f);
                }
            }
        });

        translateButton = (RadioButton) findViewById(R.id.translateRadioButton);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFade = false;
                isTranslate = true;
                isRotate = false;

                if (evolve) {
                    really.setAlpha(1f);
                    really.setTranslationX(-1000f);
                    really.setScaleX(1f);
                    really.setScaleY(1f);
                } else {
                    rich.setAlpha(1f);
                    rich.setTranslationX(1000f);
                    rich.setScaleX(1f);
                    rich.setScaleY(1f);
                }
            }
        });

        rotateButton = (RadioButton) findViewById(R.id.rotateRadioButton);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFade = false;
                isTranslate = false;
                isRotate = true;

                if (evolve) {
                    really.setAlpha(1f);
                    really.setTranslationX(0f);
                    really.setScaleX(0f);
                    really.setScaleY(0f);
                } else {
                    rich.setAlpha(1f);
                    rich.setTranslationX(0f);
                    rich.setScaleX(0f);
                    rich.setScaleY(0f);
                }
            }
        });
        isFade = true;
        fadeButton.setChecked(true);
        evolve = true;
        duurAnimatie = 2000l;
        seekBar.setProgress((int) duurAnimatie);
        really.setAlpha(0f);



    }

    public void animatie(View view) {

        if (isFade) {
            fade();
        } else if (isTranslate) {
            rotate();
        } else {
            rotateAndScale();
        }
        evolve = !evolve;
    }

    private void fade() {
        if (evolve) {
            rich.animate().alpha(0f).setDuration(duurAnimatie);
            really.animate().alpha(1f).setDuration(duurAnimatie);
        }
        else {
            rich.animate().alpha(1f).setDuration(duurAnimatie);
            really.animate().alpha(0f).setDuration(duurAnimatie);
        }

    }


    private void rotate() {
        if (evolve) {
            rich.animate().translationX(1000l).setDuration(duurAnimatie);
            really.animate().translationX(0f).setDuration(duurAnimatie);
        }
        else {
            rich.animate().translationX(0l).setDuration(duurAnimatie);
            really.animate().translationX(-1000f).setDuration(duurAnimatie);
        }

    }

    private void rotateAndScale() {
        if (evolve) {
            rich.animate().rotation(720f).scaleX(0f).scaleY(0f).setDuration(duurAnimatie);
            really.animate().rotation(-720f).scaleX(1f).scaleY(1f).setDuration(duurAnimatie);
        } else {
            rich.animate().rotation(-720f).scaleX(1f).scaleY(1f).setDuration(duurAnimatie);
            really.animate().rotation(720f).scaleX(0f).scaleY(0f).setDuration(duurAnimatie);
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

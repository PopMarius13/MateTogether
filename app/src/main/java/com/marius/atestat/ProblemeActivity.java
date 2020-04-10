package com.marius.atestat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class ProblemeActivity extends AppCompatActivity {
    private Button pclasa9;

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_DIFICULTATE="extraDificultate";


    public static final String SHARED_PREFS= "sharedPrefs";
    public static final String KEY_HIGHSCORE= "keyHighscore";

    private TextView textViewHighscore;
    private Spinner spinnerDificultate;
    private ImageView Imagine;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probleme);

        AdView madview;
        madview = findViewById(R.id.adView);
        MobileAds.initialize(this,"ca-app-pub-9513540471540461~7846055766");
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);


        pclasa9=findViewById(R.id.Pclasa9);
        textViewHighscore = findViewById(R.id.scor9);
        spinnerDificultate = findViewById(R.id.spinner_dificultate);
        Imagine = findViewById(R.id.imageView);


        String [] dificultateLevel = Question.getAllDificultateLevels();

        ArrayAdapter<String> adpaterDificultate = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dificultateLevel);
        adpaterDificultate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDificultate.setAdapter(adpaterDificultate);

        spinnerDificultate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Imagine.setImageResource(R.drawable.usor1);
                }else if(position==1) {
                    Imagine.setImageResource(R.drawable.mediu1);
                }else if(position==2) {
                    Imagine.setImageResource(R.drawable.greu1);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadHighscore();
        pclasa9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActPclasa9();
            }
        });
    }


    private void OpenActPclasa9 ()
    {
        String dificultate = spinnerDificultate.getSelectedItem().toString();

        Intent intent= new Intent(ProblemeActivity.this,PClasa9.class);
        intent.putExtra(EXTRA_DIFICULTATE,dificultate);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ) {
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(PClasa9.EXTRA_SCORE,0);
                if(score > highscore){
                    updateHighscore(score);
                }
            }
        }
    }


    private  void loadHighscore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore= prefs.getInt(KEY_HIGHSCORE, 0 );
        textViewHighscore.setText( highscore +  "/15");
    }

    private  void updateHighscore (int highscorenew){
        highscore = highscorenew;
        textViewHighscore.setText(highscore + "/15");

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }

}

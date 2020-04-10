package com.marius.atestat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button butteori;
    private Button butProbleme;
    private Button Sugesti;
    private Button TimpLiber;
    private Button Aplicatii;


    private AdView madview;
    private InterstitialAd interstitialAd;
    private TextView cale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        madview = findViewById(R.id.adView);
        MobileAds.initialize(this,"ca-app-pub-9513540471540461~7846055766");
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);

        butteori = findViewById(R.id.Teorie);
        butProbleme = findViewById(R.id.Probleme);
        Sugesti = findViewById(R.id.facebook);
        TimpLiber = findViewById(R.id.Timpliber);
        Aplicatii = findViewById(R.id.Ajutor);

        butProbleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProblemeActivity();
            }
        });

        butteori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTeorieActivity();
            }
        });

        Sugesti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gotofacebook("283771415871055");
            }
        });

        TimpLiber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTimpLiberActivity();
            }
        });

        Aplicatii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAjutorActivity();
            }
        });


    }
    private void OpenProblemeActivity(){
        Intent intent=new Intent(this,ProblemeActivity.class);
        startActivity(intent);
    }

    private void OpenTeorieActivity(){
        Intent intent=new Intent(this,TeorieActivity.class);
        startActivity(intent);
    }

    private void OpenTimpLiberActivity(){
        Intent intent = new Intent(this,TimpLiberActivity.class);
        startActivity(intent);
    }

    private void OpenAjutorActivity(){
        Intent intent = new Intent(this,Aplicatii.class);
        startActivity(intent);
    }


    private void Gotofacebook (String id)
    {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"+id));
            startActivity(intent);
        }catch (ActivityNotFoundException e)
        {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+id));
            startActivity(intent);
        }
    }
}

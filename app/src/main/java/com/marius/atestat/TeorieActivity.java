package com.marius.atestat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class TeorieActivity extends AppCompatActivity {

    private Button clasa9;
    private Button clasa10;
    private Button clasa11;
    private Button clasa12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teorie);

        AdView madview;
        madview = findViewById(R.id.adView);
        MobileAds.initialize(this,"ca-app-pub-9513540471540461~7846055766");
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);


        clasa9=findViewById(R.id.clasa9);
        clasa10=findViewById(R.id.clasa10);
        clasa11=findViewById(R.id.clasa11);
        clasa12=findViewById(R.id.clasa12);

        clasa9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActClasa9();
            }
        });

        clasa10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActClasa10();
            }
        });
        clasa11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActClasa11();
            }
        });

        clasa12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActClasa12();
            }
        });
    }

    private  void OpenActClasa9(){
        Intent intent =new Intent(this,TClasa9.class);
        startActivity(intent);
    }

    private  void OpenActClasa10(){
        Intent intent =new Intent(this,TClasa10.class);
        startActivity(intent);
    }

    private  void OpenActClasa11(){
        Intent intent =new Intent(this,TClasa11.class);
        startActivity(intent);
    }

    private  void OpenActClasa12(){
        Intent intent =new Intent(this,TClasa12.class);
        startActivity(intent);
    }
}

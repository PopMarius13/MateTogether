package com.marius.atestat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class TClasa9 extends AppCompatActivity {

    PDFView book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tclasa9);

        AdView madview;
        madview = findViewById(R.id.adView);
        MobileAds.initialize(this,"ca-app-pub-9513540471540461~7846055766");
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);


        book=(PDFView) findViewById(R.id.clasa9pdf);
        book.fromAsset("clasa9.pdf").load();

    }
}

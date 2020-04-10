package com.marius.atestat;

import android.animation.TypeConverter;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class determinant extends AppCompatActivity {

private Button calculare;
private TextView raspuns;

public EditText a,b,c,d,e,f,g,h,i;

public double a1,b1,c1,d1,e1,f1,g1,h1,i1;
double ras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determinant);

        calculare = findViewById(R.id.calculare);
        raspuns = findViewById(R.id.raspuns);

        AdView madview;
        madview = findViewById(R.id.adView);
        MobileAds.initialize(this,"ca-app-pub-9513540471540461~7846055766");
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);


        a =(EditText)findViewById(R.id.editText1);
        b = (EditText)findViewById(R.id.editText2);
        c = (EditText)findViewById(R.id.editText3);
        d = (EditText)findViewById(R.id.editText4);
        e = (EditText)findViewById(R.id.editText5);
        f = (EditText)findViewById(R.id.editText6);
        g = (EditText)findViewById(R.id.editText7);
        h = (EditText)findViewById(R.id.editText8);
        i = (EditText)findViewById(R.id.editText9);


        calculare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(a.getText().length()>0 && b.getText().length()>0 && c.getText().length()>0 && d.getText().length()>0 && e.getText().length()>0 && f.getText().length()>0 && g.getText().length()>0 && h.getText().length()>0 && i.getText().length()>0 )
                {
                    a1 = Double.parseDouble(a.getText().toString());
                    b1 = Double.parseDouble(b.getText().toString());
                    c1 = Double.parseDouble(c.getText().toString());
                    d1 = Double.parseDouble(d.getText().toString());
                    e1 = Double.parseDouble(e.getText().toString());
                    f1 = Double.parseDouble(f.getText().toString());
                    g1 = Double.parseDouble(g.getText().toString());
                    h1 = Double.parseDouble(h.getText().toString());
                    i1 = Double.parseDouble(i.getText().toString());

                   ras=a1*e1*i1+d1*h1*c1+b1*f1*g1-c1*e1*g1-a1*f1*h1-b1*d1*i1;
                   raspuns.setText("Răspuns: " + ras);
                }
                else {
                    Toast.makeText(determinant.this,"Te rog completează căsuțele",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}

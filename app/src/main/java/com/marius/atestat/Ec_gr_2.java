package com.marius.atestat;

import android.animation.TypeConverter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;

public class Ec_gr_2 extends AppCompatActivity {


    EditText a,b,c;
    private TextView del,x1,x2;
    Button calcul;

    View dro,drv;

    DecimalFormat df = new DecimalFormat("###.###");
    int a1,b1,c1;
    double del1;
    double x11,x21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ec_gr_2);

        AdView madview;
        madview = findViewById(R.id.adView);
        MobileAds.initialize(this,"ca-app-pub-9513540471540461~7846055766");
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);


        a = findViewById(R.id.editText1);
        b = findViewById(R.id.editText2);
        c = findViewById(R.id.editText3);

        del= findViewById(R.id.texx);
        x1 = findViewById(R.id.texx1);
        x2 = findViewById(R.id.texx2);

        calcul = findViewById(R.id.calcul);

        dro = findViewById(R.id.droriz);
        drv = findViewById(R.id.drvert);


        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.getText().length()>0 && c.getText().length()>0 && b.getText().length()>0 ){
                    a1 = Integer.parseInt(a.getText().toString());
                    b1 = Integer.parseInt(b.getText().toString());
                    c1 = Integer.parseInt(c.getText().toString());
                    del1=b1*b1-4*a1*c1;
                    if(del1>0) {
                        x11=-b1 + Math.sqrt(del1);
                        x21=-b1 - Math.sqrt(del1);
                        x11=x11/(2*a1);
                        x21=x21/(2*a1);
                        del.setText("∆ = √"+ del1 + " = " + df.format(Math.sqrt(del1)));
                        x1.setText("x1 = (" + (-b1) + " + " +"√"+df.format(del1)+")/(2 * "+a1+") = "+df.format(x11));
                        x2.setText("x2 = (" + (-b1) + " - " +"√"+df.format(del1)+")/(2 * "+a1+") = "+df.format(x21));

                    }
                    else
                        if(del1<0){
                            del.setText("∆ = i√"+ del1 + " = i" + df.format(Math.sqrt(-del1)));
                            x1.setText("x1 = (" + (-b1) + " + " +"i√"+df.format(-del1)+")/"+2*a1);
                            x2.setText("x2 = (" + (-b1) + " - " +"i√"+df.format(-del1)+")/"+2*a1);
                        }
                        else{
                            x11=-b1 ;
                            x21=-b1 ;
                            x11=x11/(2*a1);
                            x21=x21/(2*a1);
                            del.setText("∆ = 0");
                            x1.setText("x1 = " + (-b1)+"/(2 * "+a1+") = "+df.format(x11));
                            x2.setText("x2 = " + (-b1) +"/(2 * "+a1+") = "+df.format(x21));
                        }
                }

            }
        });
    }
}

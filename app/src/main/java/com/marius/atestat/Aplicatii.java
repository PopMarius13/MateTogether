package com.marius.atestat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Aplicatii extends AppCompatActivity {

    private Button det;
    private Button ecgr2;
    private Button graf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicatii);

        det = findViewById(R.id.deter);
        ecgr2 = findViewById(R.id.ecgr2);
        graf = findViewById(R.id.grafice);

        det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpneDetActivity();
            }
        });
        ecgr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenEcgr2Activity();
            }
        });
        graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGrafActivity();
            }
        });
    }

    private void OpneDetActivity(){
        Intent intent = new Intent(this,determinant.class);
        startActivity(intent);
    }

    private void OpenEcgr2Activity(){
        Intent intent = new Intent(this,Ec_gr_2.class);
        startActivity(intent);
    }

    private void OpenGrafActivity(){
        Intent intent = new Intent(this,Grafice.class);
        startActivity(intent);
    }

}

package com.example.tartiflette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String nomsigne = intent.getStringExtra("nomSigne");
        String datedebut = intent.getStringExtra("dateDebut");
        String datefin = intent.getStringExtra("dateFin");
    }

}

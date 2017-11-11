package com.example.sanjatmishra.newbeergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    EditText lbound, ubound, weeks, curretailer, curwholesaler, curdis, curfact, randomincrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);




    }

    public void clickedplay(View v){
        Intent myintent = new Intent(this,GameActivity.class);

        lbound = (EditText)findViewById(R.id.lbound);
        ubound = (EditText)findViewById(R.id.ubound);
        weeks = (EditText)findViewById(R.id.weeks);
        curretailer = (EditText)findViewById(R.id.stockRet);
        curwholesaler = (EditText)findViewById(R.id.stockWhole);
        curdis = (EditText)findViewById(R.id.stockDis);
        curfact = (EditText)findViewById(R.id.stockFac);
        randomincrease = (EditText)findViewById(R.id.Randinc);

        Bundle b = new Bundle();
        b.putString("week", weeks.getText().toString());
        b.putString("lbound", lbound.getText().toString());
        b.putString("ubound", ubound.getText().toString());
        b.putString("cret", curretailer.getText().toString());
        b.putString("cwhole", curwholesaler.getText().toString());
        b.putString("cdis", curdis.getText().toString());
        b.putString("cfact", curfact.getText().toString());
        b.putString("rinc", randomincrease.getText().toString());

        myintent.putExtras(b);

        InputActivity.this.startActivity(myintent);
    }


}

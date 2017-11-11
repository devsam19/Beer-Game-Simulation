package com.example.sanjatmishra.newbeergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }




    public void clickplay(View v){
        Intent myintent = new Intent(IntroActivity.this,InputActivity.class);
        IntroActivity.this.startActivity(myintent);
    }


}

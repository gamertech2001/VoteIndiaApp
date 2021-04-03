package com.example.voteindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),OTPActivity.class));
    }

    public void voterActivityStart(View view) {
        Intent i = new Intent(MainActivity.this,VoterActivity.class);
        startActivity(i);

    }
}
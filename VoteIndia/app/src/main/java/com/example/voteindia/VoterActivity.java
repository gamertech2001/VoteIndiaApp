package com.example.voteindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import soup.neumorphism.NeumorphImageButton;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class VoterActivity extends AppCompatActivity implements View.OnClickListener {

    public static boolean voteCast = false;
    public static String selection = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter);

        //all the candidate buttons
        NeumorphImageButton candidate1 = findViewById(R.id.candidate1);
        NeumorphImageButton candidate2 = findViewById(R.id.candidate2);
        NeumorphImageButton candidate3 = findViewById(R.id.candidate3);
        NeumorphImageButton candidate4 = findViewById(R.id.candidate4);


        candidate1.setOnClickListener(this);
        candidate2.setOnClickListener(this);
        candidate3.setOnClickListener(this);
        candidate4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.candidate1:
                if(voteCast){
                    makeText(this, "already voted", LENGTH_SHORT).show();
                }else{
                    selection = "Aam Aadmi Party";
                    setContentView(R.layout.popup);
                    NeumorphImageButton confirm = findViewById(R.id.confirm);
                    NeumorphImageButton cancel = findViewById(R.id.cancel);
                    TextView party = findViewById(R.id.Party);
                    ImageView pim = findViewById(R.id.pim);
                    party.setText(selection);
                    pim.setImageResource(R.drawable.candidate1);
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.endpage);
                            voteCast = true;
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(),VoterActivity.class));
                        }
                    });
                }

                break;
            case R.id.candidate2:
                if(voteCast){
                    makeText(this, "already voted", LENGTH_SHORT).show();
                }else{
                    selection = "Bharatiya Janata Party";
                    setContentView(R.layout.popup);
                    NeumorphImageButton confirm = findViewById(R.id.confirm);
                    NeumorphImageButton cancel = findViewById(R.id.cancel);
                    TextView party = findViewById(R.id.Party);
                    ImageView pim = findViewById(R.id.pim);
                    party.setText(selection);
                    pim.setImageResource(R.drawable.candidate2);
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.endpage);
                            voteCast = true;
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(),VoterActivity.class));
                        }
                    });
                }
                break;

            case R.id.candidate3:
                if(voteCast){
                    makeText(this, "already voted", LENGTH_SHORT).show();
                }else{
                    selection = "Congress";
                    setContentView(R.layout.popup);
                    NeumorphImageButton confirm = findViewById(R.id.confirm);
                    NeumorphImageButton cancel = findViewById(R.id.cancel);
                    TextView party = findViewById(R.id.Party);
                    ImageView pim = findViewById(R.id.pim);
                    party.setText(selection);
                    pim.setImageResource(R.drawable.candidate3);
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.endpage);
                            voteCast = true;
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(),VoterActivity.class));
                        }
                    });
                }
                break;

            case R.id.candidate4:
                if(voteCast){
                    makeText(this, "already voted", LENGTH_SHORT).show();
                }else{
                    selection = "Samajwadi Party";
                    setContentView(R.layout.popup);
                    NeumorphImageButton confirm = findViewById(R.id.confirm);
                    NeumorphImageButton cancel = findViewById(R.id.cancel);
                    TextView party = findViewById(R.id.Party);
                    ImageView pim = findViewById(R.id.pim);
                    party.setText(selection);
                    pim.setImageResource(R.drawable.candidate4);
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.endpage);
                            voteCast = true;
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(),VoterActivity.class));
                        }
                    });
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),VoterActivity.class));
    }

}
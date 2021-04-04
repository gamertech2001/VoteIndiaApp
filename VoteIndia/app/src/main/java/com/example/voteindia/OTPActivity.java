package com.example.voteindia;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;


public class OTPActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText phoneNumber, codeEnter;
    Button nextBtn;
    TextView state;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CountryCodePicker codePicker;
    ProgressBar progressBar;
    String verificationId;
    PhoneAuthProvider.ForceResendingToken token;
    Boolean verificationInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_activity);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        phoneNumber = findViewById(R.id.phone);
        codeEnter = findViewById(R.id.codeEnterid);
        state= findViewById(R.id.stateid);
        progressBar = findViewById(R.id.progressBar);
        nextBtn = findViewById(R.id.nextBtnid);
        codePicker = findViewById(R.id.ccp);

        nextBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(!verificationInProgress){

                    if (!phoneNumber.getText().toString().isEmpty() && phoneNumber.getText().toString().length() == 10) {

                        String phoneNum = "+" + codePicker.getSelectedCountryCode() + phoneNumber.getText().toString();
                        Log.v("TAG", "On click phone number is " + phoneNum);
                        progressBar.setVisibility(View.VISIBLE);
                        state.setText("Sending OTP...");
                        state.setVisibility(View.VISIBLE);
                        requestOTP(phoneNum);



                    } else {
                        phoneNumber.setError("Phone Number is not Valid");


                    }

                }else {

                    String userOTP = codeEnter.getText().toString();
                    if(!userOTP.isEmpty() && userOTP.length()==6 ){

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,userOTP);
                        verifyAuth(credential);

                    }else {
                        codeEnter.setError("Valid OTP is required");
                    }
                }

            }

        });


    }

    private void verifyAuth(PhoneAuthCredential credential) {
        fAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    checkUserProfile();

                }else{
                    Toast.makeText(com.example.voteindia.OTPActivity.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void requestOTP(String phoneNum) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNum, 60L, java.util.concurrent.TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                progressBar.setVisibility(View.GONE);
                state.setVisibility(View.GONE);
                codeEnter.setVisibility(View.VISIBLE);
                verificationId= s;
                token = forceResendingToken;
                nextBtn.setText("Verify");
                nextBtn.setEnabled(true);
                verificationInProgress=true;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(com.example.voteindia.OTPActivity.this,"Cannot Find account "+e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }



    private void checkUserProfile() {

        DocumentReference docRef = fStore.collection("users").document(fAuth.getCurrentUser().getUid());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(com.example.voteindia.OTPActivity.this, "Voter Not Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),OTPActivity.class));
                    finish();
                }
            }
        });
    }

}
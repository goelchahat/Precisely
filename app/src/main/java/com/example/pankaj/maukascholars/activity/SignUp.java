package com.example.pankaj.maukascholars.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pankaj.maukascholars.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by pankaj on 11/7/16.
 */
public class SignUp extends AppCompatActivity {
    EditText pass_edit, email_edit;
    String password, email, loginFlag = "0";

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        pass_edit =  findViewById(R.id.pass_edit);
        email_edit = findViewById(R.id.email_edit);
        Button button = findViewById(R.id.sign_up);
        Button signin = findViewById(R.id.sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = pass_edit.getText().toString();
                email = email_edit.getText().toString();
                if (email.length() < 5)
                    Toast.makeText(SignUp.this, "Invalid ID", Toast.LENGTH_SHORT).show();
                else if (password.length() < 5)
                    Toast.makeText(SignUp.this, "Password too short", Toast.LENGTH_SHORT).show();
                else if (email.indexOf("@")>1 && email.indexOf(".", email.indexOf("@"))>email.indexOf("@") && email.indexOf("@", email.indexOf("@"))>0){
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SignUp.this);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.apply();
                    registerUser();
                }else
                    Toast.makeText(SignUp.this, "Invalid Email ID", Toast.LENGTH_SHORT).show();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }

            return false;
        }

        return true;
    }

    void registerUser(){
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignUp.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

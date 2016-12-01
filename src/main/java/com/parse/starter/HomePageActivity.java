package com.parse.starter;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void goToLogInPage(View view) {
        Intent logInIntent = new Intent(this, LogInActivity.class);
        startActivity(logInIntent);
    }

    public void goToSignUpPage(View view) {
        Intent intentSignUp = new Intent(this, SignUpActivity.class);
        startActivity(intentSignUp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
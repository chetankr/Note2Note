package com.parse.starter;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.*;
import android.util.Log;

public class LogInActivity extends AppCompatActivity {
    public EditText editTextUserName;
    public EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

    }

    public void logIn(View view) {
        editTextUserName = (EditText)
                findViewById(R.id.editTextUserNameToLogin);
        editTextPassword = (EditText)
                findViewById(R.id.editTextPasswordToLogin);


        ParseUser.logInInBackground(editTextUserName.getText().toString(), editTextPassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if (e == null) {
                    Intent intent = new Intent(LogInActivity.this, TakePhotoActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LogInActivity.this, "Username and Password do not match.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

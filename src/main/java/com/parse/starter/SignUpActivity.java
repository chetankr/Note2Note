package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String[] qualities = {"user", "password"};
    private Spinner spin;
    private String school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    public void createAccount(View view) {
        EditText userNameText = (EditText) findViewById(R.id.editTextUserName);
        EditText passwordText = (EditText) findViewById(R.id.editTextPassword);
        EditText confirmPassText = (EditText) findViewById(R.id.editTextConfirmPassword);
        EditText nameText = (EditText) findViewById(R.id.editTextName);

        String username = userNameText.getText().toString();
        String password = passwordText.getText().toString();
        String confirmPass = confirmPassText.getText().toString();
        String name = nameText.getText().toString();

        spin = (Spinner) findViewById(R.id.schoolSpinner);
        school = spin.getSelectedItem().toString();

        if (password.equals(confirmPass) && password.length() != 0) {

            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.put("Name", name);
            user.put("School", school);
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    Intent intent = new Intent(SignUpActivity.this, TakePhotoActivity.class);
                    startActivity(intent);
                }
            });

        } else {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
        }


    }
}

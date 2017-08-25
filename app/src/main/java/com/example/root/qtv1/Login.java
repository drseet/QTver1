package com.example.root.qtv1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static com.example.root.qtv1.R.id.pass;
import static com.example.root.qtv1.R.id.username;

public class Login extends StorageUtil {

    String usr;
    EditText emailInput;
    Button adminButton;
    public static final String PREF_NAME = "USER_PREF";
    public static final String PREF_KEY = "USER_PREF_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = (EditText) findViewById(R.id.email);
        adminButton = (Button) findViewById(R.id.admin_button);


    }

    // return true if user file has been created and is stored on device
    private boolean userFound(Context context, String username) {
        File file = context.getFileStreamPath(username);
        return file.exists();
    }

    // handles account registration when user taps 'create an account'
    public void onRegTap(View v) {
        Intent reg = new Intent(Login.this, Register.class);
        startActivity(reg);
    }

    // queues up admin screen
    public void onAdminTap(View v) {
        Intent admin = new Intent(Login.this, Admin.class);
        startActivity(admin);
    }
    protected void saveUser(Context context, String user) {
        SharedPreferences pref;
        SharedPreferences.Editor edit;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        edit = pref.edit();
        edit.putString(PREF_KEY, user);
        edit.apply();
        Log.v("Login", user + " pref saved");
    }
    protected String getCurrentUser(Context context) {
        SharedPreferences pref;
        String user;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        user = pref.getString(PREF_KEY, null);
        return user;
    }

    public void onLoginTap(View v) {

        if (emailInput != null) {
            usr = emailInput.getText().toString();
        }

        // check if admin, if admin: delete user button visible --------> move to main screen ******
        if (usr.equals("admin")) {
            //make delete user/ admin button visible to allow user to access admin screen
            adminButton.setVisibility(View.VISIBLE);

        }

        if (!userFound(getApplicationContext(), usr)) {
            Toast test = Toast.makeText(getApplicationContext(),
                    "Username not found! Please create an account", Toast.LENGTH_LONG);
            test.show();
        } else {
            // save current username in shared pref and move to main screen
            saveUser(getApplicationContext(), usr);
            Intent start = new Intent(Login.this, MainActivity.class);
            startActivity(start);
        }

    }

}


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
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.example.root.qtv1.R.id.pass;

public class Login extends User {

    String usr, pw;
    EditText emailInput, passInput;
    MessageDigest md;
    Button adminButton;
    public static final String PREF_NAME = "USER_PREF";
    public static final String PREF_KEY = "USER_PREF_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = (EditText) findViewById(R.id.email);
        passInput = (EditText) findViewById(pass);
        adminButton = (Button) findViewById(R.id.admin_button);


    }

    //used to hash the user's password
    private String hash(String input) {

        try{
            md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());
            byte result[] = md.digest();
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i< result.length; ++i)
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            
            return sb.toString();
        }catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    private boolean userFound(Context context, String username) {
        File file = context.getFileStreamPath(username);
        if(file.exists()){
            return true;
        }
        else
            return false;
    }

    //handles account registration when user taps 'create an account'
    public void onRegTap(View v) {
        Intent reg = new Intent(Login.this, Register.class);
        startActivity(reg);
    }

    //queues up admin screen
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
        edit.commit();
        Log.v("QT", user + " saved");
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
            if (passInput != null)
                pw = passInput.getText().toString();
        }

        //check if admin, if admin: delete user button visible --------> move to main screen ******
        if (usr == "admin" && pw == "admin") {
            //make delete user/ admin button visible to allow user to access admin screen
            adminButton.setVisibility(View.VISIBLE);

        }
        if (userFound(getApplicationContext(), usr) == false) {
            Toast test = Toast.makeText(getApplicationContext(),
                    "Username not found! Please create an account", Toast.LENGTH_LONG);
            test.show();
        }
        else{
            saveUser(getApplicationContext(), usr);
            Intent start = new Intent(Login.this, MainActivity.class);
            startActivity(start);
        }

    }

}


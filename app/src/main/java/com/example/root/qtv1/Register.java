package com.example.root.qtv1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Register extends AppCompatActivity {

    String usr, pw, pw2;
    EditText emailInput, passInput, passInput2;
    MessageDigest md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailInput = (EditText) findViewById(R.id.email_input);
        passInput = (EditText) findViewById(R.id.pass_input);
        passInput2 = (EditText) findViewById(R.id.pass_input2);
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

    private void storeUser(String user, String pw) {
        //note: set filename as username for ease and such

        //if no password entered, return
        if(pw == null)
            return;
        //hash password
        pw = hash(pw);
        //save password
        try {
            FileOutputStream fos = openFileOutput(user, Context.MODE_PRIVATE);
            fos.write(pw.getBytes());
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onRegTap(View v){
        usr = new String();
        pw = new String();
        pw2 = new String();

        //collect user input
        usr = emailInput.getText().toString();
        pw = passInput.getText().toString();
        pw2 = passInput2.getText().toString();

        //ensure password entries match, otherwise prompt user to retry
        if(!pw.equals(pw2)){
            Toast noMatch = Toast.makeText(getApplicationContext(),
                    "Passwords do not match! Please try again"+
                            pw +"/"+ pw2, Toast.LENGTH_LONG);
            noMatch.show();
        }
        else {
            //collect user password
            pw = passInput.getText().toString();

            //save username and password in private file
            storeUser(usr, pw);

            Toast.makeText(getApplicationContext(), "stored!", Toast.LENGTH_LONG);

            //return to login screen
            Intent ret = new Intent(Register.this, Login.class);
            startActivity(ret);

        }
    }
}

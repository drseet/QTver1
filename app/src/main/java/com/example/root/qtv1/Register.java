package com.example.root.qtv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.security.MessageDigest;

public class Register extends StorageUtil {

    String usr;
    EditText emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailInput = (EditText) findViewById(R.id.email_input);
    }

    public void onRegTap(View v){
        String usr;


        //collect user input
        usr = emailInput.getText().toString();

        //save username and password in private file
        storeUser(usr);
        Log.v("Reg", "user stored");

        //return to login screen
        Intent ret = new Intent(Register.this, Login.class);
        startActivity(ret);

    }
}


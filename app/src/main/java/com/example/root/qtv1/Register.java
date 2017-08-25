package com.example.root.qtv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


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
        //usr = new String();


        //collect user input
        usr = emailInput.getText().toString();

        //save username and password in private file (removed password functionality for now)
        storeUser(usr, null);
        Log.v("Reg", "user stored" + usr);

        //return to login screen
        Intent ret = new Intent(Register.this, Login.class);
        startActivity(ret);

    }
}


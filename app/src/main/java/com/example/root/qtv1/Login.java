package com.example.root.qtv1;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    DB_Util db;
    String usr, pw;
    EditText emailInput, passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DB_Util();
        db.initDB();

        emailInput = (EditText) findViewById(R.id.email);
        passInput = (EditText) findViewById(R.id.pword);

    }

    public void onLoginTap(View v) {

        if (emailInput != null) {
            //find
            usr = emailInput.getText().toString();
            if(passInput != null)
                pw = passInput.getText().toString();
        }
        Toast test = Toast.makeText(getApplicationContext(), usr + "/" + pw, Toast.LENGTH_LONG);
        test.show();


        }


}


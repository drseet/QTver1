package com.example.root.qtv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    //deletes file for user specified
    public void onDeleteTap() {

    }

    //returns to the home screen
    public void onReturnTap() {
        Intent ret = new Intent(Admin.this, MainActivity.class);
        startActivity(ret);
    }
}

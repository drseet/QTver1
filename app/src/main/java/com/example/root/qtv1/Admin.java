package com.example.root.qtv1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    //deletes file for user specified
    public void onDeleteTap(Context context, String username) {
        File file = context.getFileStreamPath(username);
        if(file.exists())
            deleteFile(username);
    }

    //returns to the home screen
    public void onReturnTap(View v) {
        Intent ret = new Intent(Admin.this, MainActivity.class);
        startActivity(ret);
    }
}

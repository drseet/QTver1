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

public class Admin extends AppCompatActivity {

    Button delete;
    EditText usr;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        delete = (Button)findViewById(R.id.delete_button);
        usr = (EditText)findViewById(R.id.email_input);
        username = usr.getText().toString();


    }

    public void deleteUser(Context context, String username) {
        //handle separate method
        File file = context.getFileStreamPath(username);
        if(file.exists())
            deleteFile(username);
    }

    //deletes file for user specified
    public void onDeleteTap(View v) {

        deleteUser(getApplicationContext(), username);

        Toast.makeText(getApplicationContext(), username
                + " Deleted!", Toast.LENGTH_SHORT).show();
    }

    //returns to the home screen
    public void onReturnTap(View v) {
        Intent ret = new Intent(Admin.this, MainActivity.class);
        startActivity(ret);
    }
}

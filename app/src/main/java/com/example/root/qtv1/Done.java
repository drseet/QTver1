package com.example.root.qtv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Done extends MainActivity {

    //TextView duration = (TextView)findViewById(R.id.time);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        setDuration();
    }

    //get file containing the time
    public void setDuration(){
        Toast.makeText(getApplicationContext(), this.qt_mins+
                " / "+ this.usr, Toast.LENGTH_LONG).show();

    }


    public void onHomeTap(View v) {
        Intent main = new Intent(Done.this, MainActivity.class);
        finishAndRemoveTask();
        startActivity(main);

    }

}

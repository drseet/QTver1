package com.example.root.qtv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Done extends AppCompatActivity {

    TextView duration = (TextView)findViewById(R.id.time);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        setDuration();
    }
    public void setDuration(){
        MainActivity main = new MainActivity();
        int dur = main.qt_mins;
        duration.setText(dur);
    }


    public void onHomeTap(View v) {
        Intent main = new Intent(Done.this, MainActivity.class);
        finishAndRemoveTask();
        startActivity(main);

    }

    public void onQuitTap(View v) {
        System.exit(0);
    }
}

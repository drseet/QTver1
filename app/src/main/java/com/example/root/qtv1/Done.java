package com.example.root.qtv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Done extends MainActivity {

    TextView duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        duration = (TextView)findViewById(R.id.time);

    }


    public void onHomeTap(View v) {
        Intent main = new Intent(Done.this, MainActivity.class);
        finishAndRemoveTask();
        startActivity(main);

    }

}

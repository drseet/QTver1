package com.example.root.qtv1;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Done extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
    }

    public void onHomeTap(View v) {
        Intent main = new Intent(Done.this, MainActivity.class);
        startActivity(main);

    }

    public void onQuitTap(View v) {
        System.exit(0);
    }
}

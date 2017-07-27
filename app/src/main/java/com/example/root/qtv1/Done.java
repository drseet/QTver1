package com.example.root.qtv1;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Done extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        //finalChime();
        //Intent main = new Intent(Done.this, MainActivity.class);
        //startActivity(main);
    }

    //very likely completely unnecessary method
    public void finalChime () {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onHomeTap(View v) {
        Intent main = new Intent(Done.this, MainActivity.class);
        startActivity(main);

    }
}

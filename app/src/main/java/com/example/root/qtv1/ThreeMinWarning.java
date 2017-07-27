package com.example.root.qtv1;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
This screen notifies the user that there are 3 minutes left until the end of Quiet Time/class.
User can return to the home screen or exit the application.
*/

public class ThreeMinWarning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_min_warning);

        //final alert before class bell (~30 sec prior)
            new CountDownTimer(160000, 1000) {
                Intent done = new Intent(ThreeMinWarning.this, Done.class);
                public void onTick(long millisUntilFinished) {}
                public void onFinish() {
                    alert();
                    startActivity(done);
                }
            }.start();
        }

    public void onReturnTap(View v) {
        Intent home = new Intent(ThreeMinWarning.this, MainActivity.class);
        startActivity(home);
    }

    public void onQuitTap(View v) {
        System.exit(1);
    }

    public void alert() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

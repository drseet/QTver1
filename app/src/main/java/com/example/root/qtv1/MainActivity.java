package com.example.root.qtv1;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

//flags when first countdown timer has finished (3 min warning)
public class MainActivity extends Login {

    int qt_mins = 0;
    int i = 0;
    CountDownTimer ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onHelpTap(View v) {
        Intent help = new Intent(MainActivity.this, Help.class);
        startActivity(help);
    }

    /*
    Plays an audible alert - currently the program
    uses the default notification sound on the device
    */
    public void alert() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    When the user taps the start button, Calendar is used to collect the current time
    and TimePicker is used to collect the time that Quiet Time should end (specified by
    user on the home screen). Screen changes and informs user that quiet time is in
    progress. CountDownTimer runs until 3 minutes before Quiet Time is set to end, and
    initiates the 3 minute warning and changes screens to inform user this has occurred.
    Currently, the app only supports Quiet Time for >1 hr and prompts at the beginning,
    3 minute warning, and ~30 seconds before the time chosen
    */
    public void onStartTap(View v) {
        TimePicker timePick;
        timePick = (TimePicker) findViewById(R.id.timePicker);
        Calendar c = Calendar.getInstance();

        //minute in which quiet time ends
        int endMin = timePick.getCurrentMinute();

        //the current time in mins and hours
        int curMin = c.get(Calendar.MINUTE);

        Intent qt = new Intent(MainActivity.this, QT.class);
        startActivity(qt);

        Toast.makeText(getApplicationContext(),
                endMin + " / " + curMin, Toast.LENGTH_LONG);            //ERROR CHECK

        qt_mins = endMin - curMin;
        //if user does not specify a valid time, 15 min default
        if(qt_mins <= 0){
            qt_mins = 15;
        }

        String username = getCurrentUser(getApplicationContext());
        storeUserStats(username, qt_mins);

        //subtract 3 minutes and convert to milliseconds
        qt_mins = qt_mins - 3;
        qt_mins = qt_mins*60000;

        //subtract 45 seconds to allow for the final chime
        // to occur before the class bell
        qt_mins = qt_mins - 45000;

        //signify the beginning of QT
        alert();

        ct = new CountDownTimer(qt_mins, 1000) {
            Intent warn = new Intent(MainActivity.this, ThreeMinWarning.class);
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                alert();
                cancel();
                finishAndRemoveTask();
                startActivity(warn);
            }
        } .start();

    }

}

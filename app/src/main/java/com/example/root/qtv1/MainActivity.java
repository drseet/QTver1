package com.example.root.qtv1;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Hashtable;

//flags when first countdown timer has finished (3 min warning)
public class MainActivity extends Login {

    int qt_mins = 0;
    int i = 0;

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
    Records time and stored in Hashtable and passes the hashtable to be stored
    in a private file on the device.
    */
    public void recordTime(int time) {
        int i = 0;
        Hashtable<String, Integer> quietTimes = new Hashtable<>();

        /*
        keeping track of each quiet time session (qt) and use an iterator (i)
        to determine what session we're on so we know where to hash the data
        */
        if(quietTimes.containsKey("qt1"))
            while(!quietTimes.isEmpty())
                if(!quietTimes.containsKey("qt" + ++i));
                    quietTimes.put("qt"+i, time); Toast.makeText(getApplicationContext(),  //remove*******
                                                time+" / "+ i , Toast.LENGTH_LONG).show();

        storeTime(quietTimes, usr);
    }
    public void storeTime(Hashtable quietTimes, String username) {
        try {
            FileOutputStream fos = openFileOutput(username+"_qt_records", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(quietTimes);
            os.close();
            Log.v("QT", "File written");
        }catch (IOException e){
            e.printStackTrace();
            Log.v("QT", "File not written");
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

        qt_mins = endMin - curMin;
        //if user does not specify a valid time, 15 min default
        if(qt_mins <= 0){
            qt_mins = 15;
        }
        //subtract 3 minutes and convert to milliseconds
        qt_mins = qt_mins - 3;
        qt_mins = qt_mins*60000;

        //subtract 45 seconds to allow for the final chime
        // to occur before the class bell
        qt_mins = qt_mins - 45000;

        recordTime(qt_mins);

        //signify the beginning of QT
        alert();

        new CountDownTimer(qt_mins, 1000){
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

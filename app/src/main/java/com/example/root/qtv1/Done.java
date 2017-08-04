package com.example.root.qtv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;

public class Done extends MainActivity {

    TextView duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        duration = (TextView)findViewById(R.id.time);
        setDuration();
    }


    //not working currently and storage system will be redesigned for subsequent ver
    public void setDuration(){
        int time = 0;
        Hashtable<String, Integer> quietTimes = new Hashtable<>();


        try {
            FileInputStream fis = openFileInput(usr + "qt_records");
            ObjectInputStream ois = new ObjectInputStream(fis);
            quietTimes = (Hashtable) ois.readObject();
            ois.close();

        }catch (IOException e){
            e.printStackTrace();
            Log.v("QT","file not found");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.v("QT", "Class not found ?");
        }

        if(quietTimes.containsKey("qt1"))
            if(quietTimes.containsKey("qt" + i))
                time = quietTimes.get("qt" + i);

        if(time>0)
            duration.setText(time);

    }


    public void onHomeTap(View v) {
        Intent main = new Intent(Done.this, MainActivity.class);
        finishAndRemoveTask();
        startActivity(main);

    }

}

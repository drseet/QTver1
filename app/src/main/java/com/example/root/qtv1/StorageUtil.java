package com.example.root.qtv1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
contains all the storage methods
each user stored as an object in a file that is named after the username
*/
class StorageUtil extends AppCompatActivity implements java.io.Serializable {

    MessageDigest md;
    int total, sessions;

    //used to hash the user's password
    protected String hash(String input) {

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());
            byte result[] = md.digest();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < result.length; ++i)
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    protected void storeUser(String user, String pw) {
        //note: set filename as username for ease and such

        //if no password entered, return
        if (pw == null)
            return;
        //hash password
        pw = hash(pw);
        //save password
        try {
            FileOutputStream fos = openFileOutput(user, Context.MODE_PRIVATE);
            fos.write(pw.getBytes());
            fos.close();
            //create files to store QT stats
            fos = openFileOutput(user + "_qt_total", Context.MODE_PRIVATE);
            fos.write(0);
            fos.close();

            fos = openFileOutput(user + "_qt_sessions", Context.MODE_PRIVATE);
            fos.write(0);
            fos.close();

            fos = openFileOutput(user + "_qt_avg", Context.MODE_PRIVATE);
            fos.write(0);
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Storage", "Unable to create user file");
        }
    }

    protected void storeTotal(String username, int duration) {
        File file = getFileStreamPath(username);

        if (file.exists()) {
            file = getFileStreamPath(username + "_qt_total");
            if (file.exists()) {
                //get int from file and add duration to it- delete old file and create new with sum
                try {
                    //pull previous number from total file, convert to mins, close and delete the file
                    FileInputStream fis = openFileInput(username + "_qt_total");
                    total = fis.read();
                    Log.v("StorageTotal", "total = " + total);
                    fis.close();
                    deleteFile(username + "_qt_total");
                    //add the duration of current quiet time to the total, convert to mins,
                    //open/create file and save data
                    total += duration;
                    FileOutputStream fos = openFileOutput(username + "_qt_total", Context.MODE_PRIVATE);
                    fos.write(total);
                    fos.close();
                    Log.v("storage", "total saved as: " + total + " duration: " + duration);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.v("storage", "ERROR: not able to save total");
                }
            } else
                Log.v("Storage", username + " file not found!");

        }
    }

    protected void storeSessionCount(String username) {
        File file = getFileStreamPath(username);

        if (file.exists()) {
            file = getFileStreamPath(username + "_qt_sessions");
            if (file.exists()) {
                //get int from file and add 1 to it- delete old file and create new with sum
                try {
                    //pull previous number from total file (+1) close and delete the file
                    FileInputStream fis = openFileInput(username + "_qt_sessions");
                    sessions = ((fis.read()) + 1);
                    fis.close();
                    deleteFile(username + "_qt_sessions");

                    //open/create file and save updated session data
                    FileOutputStream fos = openFileOutput(username + "_qt_sessions", Context.MODE_PRIVATE);
                    fos.write(sessions);
                    fos.close();
                    Log.v("storage", "sessions saved as: " + sessions);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.v("storage", "ERROR: not able to save sessions");
                }
            } else
                Log.v("Storage", username + " file not found!");

        }
    }

    /*
    gather total quiet time and amt of sessions and calc avg. delete old file and replace w/
    new avg data
    */
    protected void storeSessionAvg(String username) {
        int avg = this.total / this.sessions;

        Log.v("Storage", "avg: " + avg);

        File file = getFileStreamPath(username);
        if (!file.exists()) {
            Log.v("Storage", "ERROR: user file not found!");
            return;
        }
        deleteFile(username + "_qt_avg");
        try {
            FileOutputStream fos = openFileOutput(username + "_qt_avg", Context.MODE_PRIVATE);
            fos.write(avg);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Storage", "ERROR: unable to save qt avg");
        }
    }

    //store quiet time stats in private files
    protected void storeUserStats(String username, int duration) {
        storeTotal(username, duration);
        storeSessionCount(username);
        storeSessionAvg(username);

    }

    protected void deleteUser(String username) {
        File file = getApplicationContext().getFileStreamPath(username);
        if (file.exists())
            deleteFile(username);
    }

}
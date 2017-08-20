package com.example.root.qtv1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
contains all the storage methods
each user stored as an object in a file that is named after the username
*/
class StorageUtil extends AppCompatActivity implements java.io.Serializable {

    File file;
    MessageDigest md;


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
            fos = openFileOutput(user + "_qt_avg", Context.MODE_PRIVATE);
            fos.write(0);
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Storage", "Unable to create user file");
        }
    }

    protected void storeUserStats(String username, int duration) {
        File file = getFileStreamPath(username);
        if (file.exists()) {
            //try{
            //FileOutputStream fos = openFileOutput(username + "_qt_total", Context.MODE_PRIVATE);
            //fos.write();
            //}catch (IOException e){

            //}
            file = getFileStreamPath(username + "_qt_total");
            if (file.exists()) {
                //get int from file and add duration to it- delete old file and create new with sum
            }
            //do same with sessions(add 1) and replace avg
        } else {
            Log.v("Storage", username + " file not found!");
        }
    }

    /*
        protected void storeUser(User user) {
            if(user == null){
                Log.v("Storage",user.name + " user is null");
            }

            try {
                FileOutputStream fos = openFileOutput(user.name, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(user);
                oos.close();
                fos.close();
                Log.v("QT", "File stored");
            } catch (IOException e) {
                e.printStackTrace();
                Log.v("QT", "ERROR: File not stored");

            }
        }

        protected User getUser(String username) {

            try {

        }
    */
    protected boolean userFound(String username) {
        file = getApplicationContext().getFileStreamPath(username);
        return file.exists();
    }

    protected void deleteUser(String username) {
        File file = getApplicationContext().getFileStreamPath(username);
        if (file.exists())
            deleteFile(username);
    }

}
package com.example.root.qtv1;

/*
define user- contains variables to keep track of total quiettime duration, as well
as the number of sessions completed
*/

import android.content.Context;
import android.widget.Toast;

import java.io.File;

public class User extends StorageUtil{

    protected String name;
    protected String pw;
    protected int qt_total, qt_avg, qt_sessions;


    /*
    name stores user email, pw holds hashed password, qt_total is the total time spent in
    quiet time. qt_sessions and qt_avg stores the number of sessions and average quiet time
    session length, respectively
    */
    public User() {
        name = null;
        pw = null;
        qt_total = 0;
        qt_sessions = 0;
        qt_avg = 0;

    }
    protected void updateUser(Context context, User user, int duration){
        File file = context.getFileStreamPath(user.name);
        if(file.exists()){
            User userUpdate = new User();
            userUpdate.name = user.name;
            userUpdate.pw = user.pw;

            userUpdate.qt_total = duration;
            userUpdate.qt_sessions = ++user.qt_sessions;
            userUpdate.qt_avg = (duration/user.qt_sessions);

            Toast.makeText(getApplicationContext(),
                    userUpdate.qt_total+ " / " + userUpdate.qt_sessions + " / " +
                            userUpdate.qt_avg, Toast.LENGTH_LONG).show();
            deleteUser(user.name);
            storeUser(userUpdate);
        }


    }
}

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

/*
contains all the storage methods
each user stored as an object in a file that is named after the username
*/
class StorageUtil extends AppCompatActivity implements java.io.Serializable {

    File file;

    protected void storeUser(User user) {
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
        User user = new User();

        try {
            FileInputStream fis = openFileInput(username);
            ObjectInputStream ois = new ObjectInputStream(fis);
            user = (User) ois.readObject();
        } catch (IOException e) {
            Log.v("QT", "ERROR: file not found");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.v("QT", "ERROR: class not found");
            e.printStackTrace();
        }

        if (user == null) {                           //fix
            Log.v("QT", "user not found");
            return null;
        } else
            return user;
    }

    protected boolean userFound(String username) {
        file = getApplicationContext().getFileStreamPath(username);
        if (file.exists()) {
            return true;
        } else
            return false;
    }

    protected void deleteUser(String username) {
        File file = getApplicationContext().getFileStreamPath(username);
        if (file.exists())
            deleteFile(username);
    }

}
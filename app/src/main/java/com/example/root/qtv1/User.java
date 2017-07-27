package com.example.root.qtv1;

//defines a user for the SQLite database

public class User {

    private String _username;
    private String _pw;

    public User() {

    }

    public User(String username, String pw) {
        this._username = username;
        this._pw = pw;
    }

    public void addUser(String username, String pw) {
        //add hash method
        this._username = username;
        this._pw = pw;
    }
    public String findUser() {
        return this._username;
    }
}

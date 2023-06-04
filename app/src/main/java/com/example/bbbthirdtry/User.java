package com.example.bbbthirdtry;

import android.util.Log;

import java.sql.Connection;

public class User {
    Connection connection = null;
    public int points = 0;
    public String name;
    public static User user;
    public static boolean created = false;

    public User (String name){
        created = true;
        user = this;
        this.name = name;
    }

    public static User getUser(){
        if(!created){
            return new User("TestUser1");
        } else {
            return user;
        }
    }
}

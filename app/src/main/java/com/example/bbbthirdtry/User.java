package com.example.bbbthirdtry;

import java.sql.Connection;

public class User {
    Connection connection = null;
    public int points = 0;
    public String name;
    public static User user;

    public User (String name){
        DatabaseConnection.connect();
        this.name = name;
        user = this;
    }

    public static User getUser(){
        if(user == null){
            return new User("TestUser1");
        } else {
            return user;
        }
    }
}

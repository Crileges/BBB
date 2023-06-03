package com.example.bbbthirdtry;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection{
        private static String ip = "127.0.0.1";
        private static String port = "5432";
        private static String Classes = "org.postgresql.Driver";
        private static String database = "BBB";
        private static String username = "postgres";
        private static String password = "Th0rge2003";
        private static String url = "jdbc:postgresql://"+ip+":"+port+"/"+database;



        public static void connect(){
                try {
                        Class.forName(Classes);
                        User.getUser().connection = DriverManager.getConnection(url, username,password);
                        Log.d("Connection", "connected");
                } catch (ClassNotFoundException e) {
                        Log.d("Connection", "not connected");
                        e.printStackTrace();
                } catch (SQLException e) {
                        Log.d("Connection", "not connected");
                        e.printStackTrace();
                }
        }

        public static ResultSet executeStatement(String query){
                try (Statement stmt = User.getUser().connection.createStatement()) {
                        ResultSet rs = stmt.executeQuery(query);
                        return rs;
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return null;
        }

        public static void writeToUserTable(int id) {
                ResultSet rs = executeStatement("select * from quests where id = " + id);
                try {
                        String userId = User.getUser().name;
                        String title = rs.getString(2);
                        int points = rs.getInt(3);
                        String description = rs.getString(4);
                        boolean done = false;
                        String category = rs.getString(6);
                        double lat = rs.getDouble(7);
                        double lon = rs.getDouble(8);
                        int radius = rs.getInt(9);

                        executeStatement("INSERT INTO public.userQuests(userId, title, points, description, " +
                                "done, category, lat, lon, radius)VALUES (" + userId + " , " + title + ", " + points +
                                ", " + description + ", " + done + ", " + category + ", " + lat + ", " + lon + ", " +radius + ");");

                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }

        }
}

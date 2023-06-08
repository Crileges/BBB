package com.example.bbbthirdtry.MainFragments.Quest;

public enum Categories {
    SIGHTSEEING,
    BAR,
    MAIN,
    THEATER,
    MUSEUM;


    public static Categories convertToCategory(String category){
        switch (category){
            case "MAIN": return MAIN;
            case "SIGHTSEEING": return SIGHTSEEING;
            case "BAR": return BAR;
            case "THEATER": return THEATER;
            case "MUSEUM": return MUSEUM;
        }
        return SIGHTSEEING;
    }
}

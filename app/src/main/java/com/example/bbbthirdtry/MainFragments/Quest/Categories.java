package com.example.bbbthirdtry.MainFragments.Quest;

public enum Categories {
    SIGHTSEEING,
    BAR,
    MAIN,
    THEATER,
    MUSEUM,
    FOOD;


    public static Categories convertToCategory(String category){
        switch (category){
            case "MAIN": return MAIN;
            case "SIGHTSEEING": return SIGHTSEEING;
            case "BAR": return BAR;
            case "THEATER": return THEATER;
            case "MUSEUM": return MUSEUM;
            case "FOOD": return FOOD;
        }
        return SIGHTSEEING;
    }
}

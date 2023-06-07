package com.example.bbbthirdtry.MainFragments.Quest;

public enum Categories {
    SIGHTSEEING,
    BAR,
    THEATER,
    MUSEUM;


    public static Categories convertToCategory(String category){
        switch (category){
            case "SIGHTSEEING": return SIGHTSEEING;
            case "BAR": return BAR;
            case "THEATER": return THEATER;
            case "MUSEUM": return MUSEUM;
        }
        return null;
    }
}

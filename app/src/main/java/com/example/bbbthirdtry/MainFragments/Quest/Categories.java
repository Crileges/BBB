package com.example.bbbthirdtry.MainFragments.Quest;

public enum Categories {
    SIGHTSEEING,
    BAR,
    THEATER;

    public static int getCategoryFromValue(Categories category){
        switch (category){
            case SIGHTSEEING: return 50;
            case BAR: return 100;
            case THEATER: return 200;
        }
        return 0;
    }

    public static Categories convertToCategory(String category){
        switch (category){
            case "SIGHTSEEING": return SIGHTSEEING;
            case "BAR": return BAR;
            case "THEATER": return THEATER;
        }
        return null;
    }
}

package com.example.bbbthirdtry.MainFragments.Quest;

public enum Points {
    FIFTY,
    ONEHUDRED,
    TWOHUNDRED;

    public static int getIntFromValue(Points points){
        switch (points){
            case FIFTY: return 50;
            case ONEHUDRED: return 100;
            case TWOHUNDRED: return 200;
        }
        return 0;
    }
}

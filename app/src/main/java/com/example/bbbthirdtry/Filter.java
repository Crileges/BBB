package com.example.bbbthirdtry;

import android.util.Log;

public class Filter {

    String filterTitle = "";
    boolean filterDone = false;

    public String getFilterTitle() {
        return filterTitle;
    }

    public boolean isFilterDone() {
        return filterDone;
    }

    public void setFilterTitle(String filterTitle) {
        this.filterTitle = filterTitle;
    }

    public void setFilterDone(boolean filterDone) {
        this.filterDone = filterDone;
    }

    public boolean getFilterDone() {
        return filterDone;
    }
}

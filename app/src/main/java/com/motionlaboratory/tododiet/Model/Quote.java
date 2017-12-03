package com.motionlaboratory.tododiet.Model;

/**
 * Created by naofal on 8/8/2017.
 */

public class Quote {
    private String quotes, name;

    public Quote(String quotes, String name) {
        this.quotes = quotes;
        this.name = name;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

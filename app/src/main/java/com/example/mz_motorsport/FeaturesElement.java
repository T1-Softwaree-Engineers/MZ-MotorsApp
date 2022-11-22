package com.example.mz_motorsport;

import java.io.Serializable;

public class FeaturesElement implements Serializable {

    private String text;

    public FeaturesElement(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.example.citati12;

import jakarta.validation.constraints.NotBlank;

public class Quote {
    private int id;
    @NotBlank(message = "Text cannot be blank")
    private String text;

    public Quote(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
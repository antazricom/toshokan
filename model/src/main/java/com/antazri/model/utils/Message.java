package com.antazri.model.utils;

import java.util.ResourceBundle;

public class Message {

    private int code;
    private String text;

    protected static final ResourceBundle APP_MESSAGES = ResourceBundle.getBundle("com/antazri/service/messages.properties");

    public Message() {
    }

    public Message(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ResourceBundle getAppMessages() {
        return APP_MESSAGES;
    }
}

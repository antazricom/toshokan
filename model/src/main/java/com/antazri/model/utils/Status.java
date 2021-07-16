package com.antazri.model.utils;

public enum Status {

    NEW ("new"),
    ONGOING ("ongoing"),
    COMPLETE ("complete");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

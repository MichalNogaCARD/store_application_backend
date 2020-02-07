package com.app.model;

public enum Role {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    SUPER("ROLE_SUPER");

    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
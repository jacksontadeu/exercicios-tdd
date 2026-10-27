package org.example.person;

public class Email {

    private final int id;
    private final String name;

    public Email(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
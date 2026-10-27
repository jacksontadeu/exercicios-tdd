package org.example.person;

import java.util.List;

public class Person {

    private final int id;
    private final String name;
    private final int age;
    private final List<Email> emails; // 1..* (possui)

    public Person(int id, String name, int age, List<Email> emails) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.emails = emails;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Email> getEmails() {
        return emails;
    }
}
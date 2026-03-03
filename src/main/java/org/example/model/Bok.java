package org.example.model;

public class Bok {

    private int id;
    private String title;
    private String author;
    private boolean available;

    public Bok(int id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author +
                (available ? " | Available" : " | Borrowed");
    }
}
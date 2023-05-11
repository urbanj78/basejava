package ru.basejava.webapp.model;

import java.util.Objects;

public class Link {
    private final String name;
    private final String link;

    public Link(String name, String link) {
        Objects.requireNonNull(name, "name must not be null");
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "HTTP_Link{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
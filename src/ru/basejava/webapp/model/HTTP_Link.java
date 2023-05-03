package ru.basejava.webapp.model;

public class HTTP_Link {
    private final String name;
    private final String link;

    public HTTP_Link(String name, String link) {
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

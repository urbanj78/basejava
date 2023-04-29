package ru.basejava.webapp.model;

import java.util.List;

public class Company {
    private final String name;
    private final HTTP_Link webSite;
    private final List<Period> periods;

    public Company(String name, HTTP_Link webSite, List<Period> periods) {
        this.name = name;
        this.webSite = webSite;
        this.periods = periods;
    }

    @Override
    public String toString() {
        return name;
    }
}

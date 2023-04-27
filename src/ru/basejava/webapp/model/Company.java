package ru.basejava.webapp.model;

import java.util.List;

public class Company {
    private String name;
    private String webSite;
    private List<Period> periods;

    @Override
    public String toString() {
        return name;
    }
}

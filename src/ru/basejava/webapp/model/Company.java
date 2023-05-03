package ru.basejava.webapp.model;

import java.util.List;

public class Company {
    //private final String name;
    private final HTTP_Link webSite;
    private final List<Period> periods;

    public Company(HTTP_Link webSite, List<Period> periods) {
        //this.name = name;
        this.webSite = webSite;
        this.periods = periods;
    }

    public HTTP_Link getWebSite() {
        return webSite;
    }

    public String getPeriods() {
        StringBuilder sb = new StringBuilder();
        for(Period period : periods) {
            sb.append(period.getPeriod());
            sb.append("\n");
        }
        return sb.toString();
    }
}

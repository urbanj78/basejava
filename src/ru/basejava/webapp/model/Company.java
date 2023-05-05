package ru.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {
    //private final String name;
    private final Link website;
    private final List<Period> periods;

    public Company(Link webSite, List<Period> periods) {
        //this.name = name;
        this.website = webSite;
        this.periods = periods;
    }

    public Link getWebsite() {
        return website;
    }

    public String getPeriods() {
        StringBuilder sb = new StringBuilder();
        for (Period period : periods) {
            sb.append(period.getPeriod());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return Objects.equals(website, company.website) && Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(website, periods);
    }

    @Override
    public String toString() {
        return "Company{" +
                "website=" + website +
                ", periods=" + periods +
                '}';
    }
}

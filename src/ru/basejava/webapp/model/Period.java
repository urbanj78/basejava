package ru.basejava.webapp.model;

import java.util.Objects;

public class Period {
    private final String dateStart;
    private final String dateEnd;
    private final String title;
    private final String description;

    public Period(String dateStart, String dateEnd, String title, String description) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period period)) return false;
        return Objects.equals(dateStart, period.dateStart) && Objects.equals(dateEnd, period.dateEnd) && Objects.equals(title, period.title) && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateEnd, title, description);
    }
}

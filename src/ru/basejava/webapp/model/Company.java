package ru.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.basejava.webapp.util.DateUtil.NOW;
import static ru.basejava.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Link website;
    private List<Period> periods = new ArrayList<>();

    public Company(String name, String url, Period... periods) {
        this(new Link(name, url), Arrays.asList(periods));
    }

    public Company(Link website, List<Period> periods) {
        this.website = website;
        this.periods = periods;
    }

    public Link getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company that = (Company) o;
        return Objects.equals(website, that.website) &&
                Objects.equals(periods, that.periods);
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


    public static class Period implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        private final LocalDate dateStart;
        private final LocalDate dateEnd;
        private final String title;
        private final String description;

        public Period(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Period(LocalDate dateStart, LocalDate dateEnd, String title, String description) {
            Objects.requireNonNull(dateStart, "dateStart must not be null");
            Objects.requireNonNull(dateEnd, "dateEnd must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return dateStart;
        }

        public LocalDate getEndDate() {
            return dateEnd;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return Objects.equals(dateStart, period.dateStart) &&
                    Objects.equals(dateEnd, period.dateEnd) &&
                    Objects.equals(title, period.title) &&
                    Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateStart, dateEnd, title, description);
        }

        @Override
        public String toString() {
            return "Period{" +
                    "dateStart=" + dateStart +
                    ", dateEnd=" + dateEnd +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}

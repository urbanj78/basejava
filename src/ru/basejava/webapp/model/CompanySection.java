package ru.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class CompanySection {
    private List<Company> companies;

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanySection that)) return false;
        return Objects.equals(getCompanies(), that.getCompanies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompanies());
    }
}
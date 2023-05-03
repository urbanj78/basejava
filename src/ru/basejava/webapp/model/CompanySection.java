package ru.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {
    private final List<Company> companies;

    public CompanySection(List<Company> companies) {
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

    @Override
    public String sectionForPrint() {
        StringBuilder sb = new StringBuilder();
        for (Company company : companies) {
            sb.append(company.getWebSite().getName());
            sb.append("\n");
            sb.append(company.getWebSite().getLink());
            sb.append("\n");
            sb.append(company.getPeriods());
        }
        return sb.toString();
    }
}


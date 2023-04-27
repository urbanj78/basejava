package ru.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection {
    protected final List<String> list;

    public ListSection(List<String> list) {
        this.list = list;
    }
    public List<String> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection that)) return false;
        return Objects.equals(getList(), that.getList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getList());
    }
}
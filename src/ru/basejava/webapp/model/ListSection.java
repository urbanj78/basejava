package ru.basejava.webapp.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<String> list;

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> list) {
        Objects.requireNonNull(list, "list must not be null");
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

    @Override
    public String toString() {
        return "ListSection{" +
                "list=" + list +
                '}';
    }
}

package ru.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> list;

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

    @Override
    public String toString() {
        return "ListSection{" +
                "list=" + list +
                '}';
    }

    @Override
    public String sectionForPrint() {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < list.size() - 1) {
            sb.append("\n");
            sb.append(list.get(i));
            sb.append("\n");
            i++;
        }
        return sb.toString();
    }
}

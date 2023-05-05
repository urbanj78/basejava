package ru.basejava.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    protected String textSection;

    public TextSection(String textSection) {
        this.textSection = textSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextSection that)) return false;
        return Objects.equals(textSection, that.textSection);
    }

    @Override
    public int hashCode() {
        return textSection.hashCode();
    }

    @Override
    public String toString() {
        return textSection;
    }
}

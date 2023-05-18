package ru.basejava.webapp.model;

import java.io.Serial;
import java.util.Objects;

public class TextSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;
    protected String textSection;

    public TextSection(String textSection) {
        Objects.requireNonNull(textSection, "textSection must not be null");
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

package ru.basejava.webapp.model;

import java.io.Serial;
import java.util.Objects;

public class TextSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;
    protected String text;

    public TextSection(String text) {
        Objects.requireNonNull(text, "textSection must not be null");
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextSection that)) return false;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        return text;
    }

    public String getText() {
        return text;
    }
}

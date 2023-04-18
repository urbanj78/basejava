package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Collection;

public interface Storage {
    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    int size();

    Collection<Resume> getAllSorted();
}

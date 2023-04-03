package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new LinkedList<>();

    @Override
    public void clear() {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}

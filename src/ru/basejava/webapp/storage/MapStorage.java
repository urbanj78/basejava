package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Hashtable;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new Hashtable<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {
        String key = r.getUuid();
        if (storage.get(key) == null) {
            storage.put(key, r);
        } else {
            throw new ExistStorageException(key);
        }
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
        return storage.entrySet().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return 0;
    }
}

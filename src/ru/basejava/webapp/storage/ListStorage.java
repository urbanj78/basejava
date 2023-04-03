package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        if (storage.contains(r)) {
            storage.set(storage.indexOf(r), r);
            System.out.println("Резюме " + r.getUuid() + " обновлено!");
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
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

    @Override
    protected int getIndex(String uuid) {
        Iterator<Resume> iterator = storage.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
    }
}

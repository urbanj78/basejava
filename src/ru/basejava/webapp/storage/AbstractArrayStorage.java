package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected int size = 0;
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("ERROR  Резюме " + uuid + " нет в хранилище!");
            return null;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " обновлено!");
        } else {
            System.out.println("ERROR  Резюме " + r.getUuid() + " нет в хранилище!");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public abstract void save(Resume r);

    public abstract void delete(String uuid);

    protected abstract int getIndex(String uuid);

}

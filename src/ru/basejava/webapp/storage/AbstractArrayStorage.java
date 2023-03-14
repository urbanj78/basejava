package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected int size = 0;
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

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

    protected abstract int getIndex(String uuid);

}

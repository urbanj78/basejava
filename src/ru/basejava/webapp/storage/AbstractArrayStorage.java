package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected int size = 0;
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR  Резюме " + r.getUuid() + " нельзя добавить, хранилище переполнено!");
        } else if (getIndex(r.getUuid()) < 0) {
            doSave(r);
        } else {
            System.out.println("ERROR  Резюме " + r.getUuid() + " уже есть в хранилище!");
        }
    }


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDelete(index);
        } else {
            System.out.println("ERROR  Резюме " + uuid + " нет в хранилище!");
        }
    }

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

    public abstract void doSave(Resume r);

    public abstract void doDelete(int index);

    protected abstract int getIndex(String uuid);
}

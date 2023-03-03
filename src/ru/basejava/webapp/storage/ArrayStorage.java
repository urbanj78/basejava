package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;

    }

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR  Резюме " + r.getUuid() + " нельзя добавить, хранилище переполнено!");
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println("ERROR  Резюме " + r.getUuid() + " уже есть в хранилище!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Резюме " + r.getUuid() + " обновлено!");
        } else {
            System.out.println("ERROR  Резюме " + r.getUuid() + " нет в хранилище!");
        }
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

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
        System.out.println("ERROR  Резюме " + uuid + " нет в хранилище!");
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}

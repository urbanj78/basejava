package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    final Resume[] STORAGE_LIMIT = new Resume[10000];

    public void clear() {
        Arrays.fill(STORAGE_LIMIT, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == STORAGE_LIMIT.length) {
            errMsg(r.getUuid(), 2);
        } else if (position(r.getUuid()) >= 0) {
            errMsg(r.getUuid(), 1);
        } else {
            STORAGE_LIMIT[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        if (position(r.getUuid()) >= 0) {
            System.out.println("Резюме " + r.getUuid() + " обновлено!");
        } else errMsg(r.getUuid(), 0);
    }

    public Resume get(String uuid) {
        if (position(uuid) >= 0) {
            return STORAGE_LIMIT[position(uuid)];
        } else {
            errMsg(uuid, 0);
            return null;
        }
    }

    public void delete(String uuid) {
        if (position(uuid) >= 0) {
            STORAGE_LIMIT[position(uuid)] = STORAGE_LIMIT[size - 1];
            STORAGE_LIMIT[size - 1] = null;
            size--;
        } else errMsg(uuid, 0);
    }

    int position(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE_LIMIT[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    void errMsg(String uuid, int err) {
        String[] msg = new String[]{" нет в хранилище!", " уже есть в хранилище!", " нельзя добавить, хранилище переполнено!"};
        System.out.println("ERROR  Резюме " + uuid + msg[err]);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE_LIMIT, size);
    }

    public int size() {
        return size;
    }
}

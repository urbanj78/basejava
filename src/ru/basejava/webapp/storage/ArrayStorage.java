package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
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


    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR  Резюме " + uuid + " нет в хранилище!");
        }
    }
    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}

package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR  Резюме " + r.getUuid() + " нельзя добавить, хранилище переполнено!");
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println("ERROR  Резюме " + r.getUuid() + " уже есть в хранилище!");
        } else if (size > 0) {
            int index = -(Arrays.binarySearch(storage, 0, size - 1, r));
            if (r.compareTo(storage[size - 1]) > 0) {
                storage[size] = r;
            } else {
                System.arraycopy(storage, index, storage, index + 1, size - index);
                storage[index] = r;
            }
            size++;
        } else {
            storage[size] = r;
            size++;
        }
    }


    @Override

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR  Резюме " + uuid + " нет в хранилище!");
        }
    }


    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
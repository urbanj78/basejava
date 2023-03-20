package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void doSave(Resume r) {
        if (size == 0) {
            storage[size] = r;
            size++;
        } else if (size == 1) {
            if (r.compareTo(storage[size - 1]) > 0) {
                storage[size] = r;
            } else {
                storage[size] = storage[size - 1];
                storage[size - 1] = r;
            }
            size++;
        } else {
            int index = -(Arrays.binarySearch(storage, 0, size - 1, r));
            if (index == 1) {
                index = 0;
            }
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
        }

    }

    @Override
    public void doDelete(int index) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[size - 1] = null;
            size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

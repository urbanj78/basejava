package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void doSave(Resume r, int index) {
        int insertionIndex = -index - 1;
        if (insertionIndex == size) {
            storage[size] = r;
        } else {
            System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, size - insertionIndex);
            storage[insertionIndex] = r;
        }
    }

    @Override
    public void doDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

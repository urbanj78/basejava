package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void finSave(Resume r, Object index) {
        int insertionIndex = -(int) index - 1;
        if (insertionIndex == size) {
            storage[size] = r;
        } else {
            System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, size - insertionIndex);
            storage[insertionIndex] = r;
        }
    }

    @Override
    public void finDelete(Object index) {
        System.arraycopy(storage, (int) index + 1, storage, (int) index, size - (int) index - 1);
    }

    @Override
    protected boolean isExist(Object index) {
        return (int) index >= 0;
    }

    @Override
    protected List<Resume> doGetAll() {
        ArrayList<Resume> list = new ArrayList<>();
        Collections.addAll(list, storage);
        return list;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "");
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

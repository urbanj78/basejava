package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void finSave(Resume r, Object index) {
        storage[size] = r;
    }

    @Override
    public void finDelete(Object index) {
        storage[(int) index] = storage[size - 1];
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
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}

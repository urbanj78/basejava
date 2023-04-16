package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage.set((int) index, r);
    }

    @Override
    public void doSave(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    public Resume doGet(Object index) {
        return storage.get((int) index);
    }

    @Override
    public void doDelete(Object index) {
        storage.remove((int) index);
    }

    @Override
    public List<Resume> getAllSorted() {
        Comparator<Resume> ResumeComparator = (o1, o2) -> {
            int i = o1.getFullName().compareTo(o2.getFullName());
            if (i == 0) {
                return o1.getUuid().compareTo(o2.getUuid());
            } else {
                return i;
            }
        };
        storage.sort(ResumeComparator);
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }
}

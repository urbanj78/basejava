package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public boolean doUpdate(Resume r) {
        if (storage.contains(r)) {
            storage.set(storage.indexOf(r), r);
            return true;
        }
        return false;
    }

    @Override
    public boolean doSave(Resume r) {
        if (!storage.contains(r)) {
            storage.add(r);
            return true;
        }
        return false;
    }

    @Override
    public Resume doGet(String uuid) {
        Resume result = null;
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                result = r;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean doDelete(String uuid) {
        return storage.removeIf(resume -> resume.getUuid().equals(uuid));
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        Iterator<Resume> iterator = storage.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getUuid().equals(uuid)) {
                return storage.indexOf(iterator.next());
            }
        }
        return -1;
    }
}
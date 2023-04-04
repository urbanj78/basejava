package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    @Override
    public boolean doUpdate(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            return true;
        }
        return false;
    }

    @Override
    public boolean doSave(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR  Резюме " + r.getUuid() + " нельзя добавить, хранилище переполнено!", r.getUuid());
        } else if (index >= 0) {
            return false;
        } else {
            doSave(r, index);
            size++;
            return true;
        }
    }

    @Override
    public Resume doGet(String uuid) {
        Resume result = null;
        int index = getIndex(uuid);
        if (index >= 0) {
            result = storage[index];
        }
        return result;
    }

    @Override
    public boolean doDelete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDelete(index);
            storage[size - 1] = null;
            size--;
            return true;
        }
        return false;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public abstract void doSave(Resume r, int index);

    public abstract void doDelete(int index);


}

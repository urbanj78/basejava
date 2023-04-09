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
    protected void doUpdate(Resume r, Object index) {
        storage[(int) index] = r;
    }

    @Override
    public void doSave(Resume r, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR  Резюме " + r.getUuid() + " нельзя добавить, хранилище переполнено!", r.getUuid());
        } else {
            finSave(r, index);
            size++;
        }
    }

    @Override
    protected void doDelete(Object index){
        finDelete(index);
        size--;
    }

    @Override
    public Resume doGet(Object index) {
        return storage[(int) index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    //@Override
    //protected abstract Integer getSearchKey(String uuid);

    protected abstract boolean isExist(Object index);

    protected abstract void finSave(Resume r, Object index);

    protected abstract void finDelete(Object index);
}

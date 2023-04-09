package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    public void doSave(Resume r, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR  Резюме " + r.getUuid() + " нельзя добавить, хранилище переполнено!", r.getUuid());
        } else {
            finSave(r, index);
            size++;
        }
    }

    @Override
    protected void doDelete(Integer index){
        finDelete(index);
        size--;
    }

    @Override
    public Resume doGet(Integer index) {
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    //@Override
    //protected abstract Integer getSearchKey(String uuid);

    protected abstract boolean isExist(Integer index);

    protected abstract void finSave(Resume r, Integer index);

    protected abstract void finDelete(Integer index);
}

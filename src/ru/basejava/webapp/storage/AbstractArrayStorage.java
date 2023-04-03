package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected int size = 0;

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR  Резюме " + r.getUuid() + " нельзя добавить, хранилище переполнено!", r.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDelete(index);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " обновлено!");
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public abstract void doSave(Resume r, int index);

    public abstract void doDelete(int index);


}

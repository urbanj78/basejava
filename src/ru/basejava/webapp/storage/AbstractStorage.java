package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume r) {
        if (doUpdate(r)) {
            System.out.println("Резюме " + r.getUuid() + " обновлено!");
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void save(Resume r) {
        if (!doSave(r)) {
            throw new ExistStorageException(r.getUuid());
        }
    }

    public Resume get(String uuid) {
        Resume result = doGet(uuid);
        if(result == null) {
            throw new NotExistStorageException(uuid);
        }
        return result;
    }

    public void delete(String uuid) {
        if (!doDelete(uuid)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract boolean doUpdate(Resume r);

    protected abstract boolean doSave(Resume r);

    protected abstract Resume doGet(String uuid);

    protected abstract boolean doDelete(String uuid);

    protected abstract int getIndex(String uuid);
}

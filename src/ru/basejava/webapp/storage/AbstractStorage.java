package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.model.Resume;

public abstract class AbstractStorage<S> implements Storage {
    @Override
    public void update(Resume r) {
        S searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
        System.out.println("Резюме " + r.getUuid() + " обновлено!");
    }

    public void save(Resume r) {
        S searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public Resume get(String uuid) {
        S searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        S searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    private S getExistingSearchKey(String uuid) {
        S searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private S getNotExistingSearchKey(String uuid) {
        S searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    protected abstract void doUpdate(Resume r, S searchKey);

    protected abstract void doSave(Resume r, S searchKey);

    protected abstract Resume doGet(S searchKey);

    protected abstract void doDelete(S searchKey);

    protected abstract S getSearchKey(String uuid);

    protected abstract boolean isExist(S searchKey);
}

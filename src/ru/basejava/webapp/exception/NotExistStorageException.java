package ru.basejava.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("ERROR: Resume " + uuid + " not exist", uuid);
    }
}

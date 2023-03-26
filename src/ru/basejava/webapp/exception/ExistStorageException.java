package ru.basejava.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("ERROR  Резюме " + uuid + " уже есть в хранилище!", uuid);
    }
}

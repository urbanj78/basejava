package ru.basejava.webapp.storage;

public class FileStorageTest extends AbstractStorageTest {
    FileStorageTest() {
        super(new FileStorage(STORAGE_DIR.getAbsolutePath()));
    }
}

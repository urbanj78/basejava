package ru.basejava.webapp.storage;

public class PathStorageTest extends AbstractStorageTest {
    PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath()));
    }
}

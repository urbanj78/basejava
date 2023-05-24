package ru.basejava.webapp.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    ObjectStreamPathStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}

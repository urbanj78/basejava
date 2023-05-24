package ru.basejava.webapp.storage;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}

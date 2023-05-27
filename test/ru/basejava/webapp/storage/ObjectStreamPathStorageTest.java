package ru.basejava.webapp.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializing()));
    }
}

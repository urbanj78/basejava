package ru.basejava.webapp.storage;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {
    ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializing()));
    }
}

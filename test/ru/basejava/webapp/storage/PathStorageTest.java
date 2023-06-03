package ru.basejava.webapp.storage;

import ru.basejava.webapp.storage.serializing.JsonStreamSerializer;
import ru.basejava.webapp.storage.serializing.ObjectStreamSerializing;

public class PathStorageTest extends AbstractStorageTest {
    PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializing()));
    }
}

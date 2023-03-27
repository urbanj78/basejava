package ru.basejava.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

class AbstractArrayStorageTest {

    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        //storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }


    @Test
    void save() {
        Resume r = new Resume("uuid4");
        storage.save(r);
        Assertions.assertEquals(r, storage.get("uuid4"));
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    void saveExistStorage() {
        Resume r = new Resume("uuid1");
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(r);
        });
    }

    @Test
    void delete() {
        storage.delete("uuid2");
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void get() {
        Resume r = new Resume(UUID_2);
        Assertions.assertEquals(r, storage.get("uuid2"));
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    void update() {
        Resume r = new Resume(UUID_1);
        storage.update(r);
        Assertions.assertEquals(r, storage.get("uuid1"));
    }

    @Test
    void updateNotExist() {
        Resume r = new Resume("dummy");
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(r);
        });
    }

    @Test
    void getAll() {
        Resume[] getAllResult = storage.getAll();
        Resume[] arrayToCompare = new Resume[3];
        final String UUID_1 = "uuid1";
        final String UUID_2 = "uuid2";
        final String UUID_3 = "uuid3";
        arrayToCompare[0] = new Resume(UUID_1);
        arrayToCompare[1] = new Resume(UUID_2);
        arrayToCompare[2] = new Resume(UUID_3);

        Assertions.assertArrayEquals(getAllResult, arrayToCompare);
    }

    @Test
    void storageOverflow() {
            for (int i = storage.size(); i < 10000; i++) {
                Assertions.assertNotEquals(10000, i, "ERROR  Хранилище переполнено раньше времени!");
                storage.save(new Resume());
        }
        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume());
        });

    }
}
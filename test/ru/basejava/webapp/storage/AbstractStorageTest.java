package ru.basejava.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;
import ru.basejava.webapp.model.ResumeTestData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("C:\\projects\\storage");

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";

    private static final String FULLNAME_1 = "John Dow";
    private static final String FULLNAME_2 = "Василий Тёркин";
    private static final String FULLNAME_3 = "Prime Optimus";
    private static final String FULLNAME_4 = "Пётр Пустота";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;
    private static final Resume RESUME_NOT_EXIST;

    static {
        RESUME_1 = ResumeTestData.fillResume(UUID_1, FULLNAME_1);
        RESUME_2 = ResumeTestData.fillResume(UUID_2, FULLNAME_2);
        RESUME_3 = ResumeTestData.fillResume(UUID_3, FULLNAME_3);
        RESUME_4 = ResumeTestData.fillResume(UUID_4, FULLNAME_4);
        RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST);
    }

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        //storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void clear() throws IOException {
        storage.clear();
        assertSize(0);

        Resume[] arrayToCompare = new Resume[0];
        Assertions.assertArrayEquals(arrayToCompare, storage.getAllSorted().toArray(new Resume[0]));
    }

    @Test
    void update() {
        Resume r = RESUME_1;
        storage.update(r);
        Assertions.assertEquals(r, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_NOT_EXIST));
    }

    @Test
    void save() throws IOException {
        storage.save(RESUME_4);
        assertGet(storage.get(UUID_4));
        assertSize(4);
    }

    @Test
    void saveExistStorage() throws IOException {
        assertSize(3);
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(RESUME_2));
    }

    @Test
    void get() {
        assertGet(storage.get(UUID_1));
        assertGet(storage.get(UUID_2));
        assertGet(storage.get(UUID_3));
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    @Test
    void delete() throws IOException {
        storage.delete(UUID_2);
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_2));
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_NOT_EXIST));
    }

    @Test
    void size() throws IOException {
        assertSize(3);
    }

    @Test
    void getAll() {

        final String UUID_1 = "uuid1";
        final String UUID_2 = "uuid2";
        final String UUID_3 = "uuid3";

        final String FULLNAME_1 = "John Dow";
        final String FULLNAME_2 = "Василий Тёркин";
        final String FULLNAME_3 = "Prime Optimus";

        List<Resume> actual = new ArrayList<>();

        actual.add(new Resume(UUID_1, FULLNAME_1));
        actual.add(new Resume(UUID_2, FULLNAME_2));
        actual.add(new Resume(UUID_3, FULLNAME_3));

        Comparator<Resume> ResumeComparator = Comparator.comparing(Resume::getFullName, (o1, o2) -> {
            if (o1.compareTo(o2) == 0) {
                Comparator.comparing(Resume::getUuid, Comparator.naturalOrder());
            }
            return o1.compareTo(o2);
        });

        actual.sort(ResumeComparator);

        Assertions.assertIterableEquals(storage.getAllSorted(), actual);
    }

    @Test
    void storageOverflow() throws IOException {
        Assumptions.assumeFalse(storage instanceof ListStorage || storage instanceof MapUUIDStorage || storage instanceof MapResumeStorage || storage instanceof ObjectStreamStorageTest || storage instanceof ObjectStreamPathStorageTest, "No test required");
        storage.clear();

        for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException exception) {
                Assertions.fail("ERROR  Хранилище переполнено раньше времени!");
            }
        }

        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    private void assertSize(int size) throws IOException {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }
}
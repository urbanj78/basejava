import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int usedSize = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i <= usedSize; i++) {
            storage[i] = null;
        }
        usedSize = 0;
    }

    void save(Resume r) {
        storage[usedSize] = r;
        usedSize++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < usedSize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < usedSize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                System.arraycopy(storage, i + 1, storage, i, usedSize - (i + 1));
                storage[usedSize] = null;
                usedSize--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, usedSize);
    }

    int size() {
        return usedSize;
    }
}

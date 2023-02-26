import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    final Resume[] STORAGE_LIMIT = new Resume[10000];

    void clear() {
        Arrays.fill(STORAGE_LIMIT, 0, size - 1, null);
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (STORAGE_LIMIT[i].uuid.equals(r.uuid)) {
                System.out.println(r.uuid + " уже существует!");
                return;
            }
        }
        STORAGE_LIMIT[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE_LIMIT[i].uuid.equals(uuid)) {
                return STORAGE_LIMIT[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE_LIMIT[i].uuid.equals(uuid)) {
                STORAGE_LIMIT[i] = STORAGE_LIMIT[size - 1];
                STORAGE_LIMIT[size - 1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(STORAGE_LIMIT, size);
    }

    int size() {
        return size;
    }
}

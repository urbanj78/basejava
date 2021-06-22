import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = size(); i >= 0; --i) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size()] = r;
        size++;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null && resume.uuid.equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i] != null) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = null;
                }
            }
        }
        bubbleSorter();
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        return size;
    }

    public void bubbleSorter() {
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < size(); i++) {
                if (storage[i] == null && storage[i + 1] != null) {
                    sorted = false;
                    Resume temp = storage[i];
                    storage[i] = storage[i + 1];
                    storage[i + 1] = temp;
                }
            }
        }
    }
}

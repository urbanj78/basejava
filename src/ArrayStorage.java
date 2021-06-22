import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = size(); i >= 0; --i) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null && resume.uuid.equals(uuid)) {
                return resume;
            }
        }
        return new Resume();
    }

    void delete(String uuid) {
        for (Resume resume : storage) {
            if (resume.uuid != null && resume.uuid.equals(uuid)) {
                resume.uuid = null;
                bubbleSorter();
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return storage.length;
    }

    public void bubbleSorter() {
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < size(); i++) {
                if (storage[i].uuid == null && storage[i + 1] != null) {
                    sorted = false;
                    Resume temp = storage[i];
                    storage[i] = storage[i + 1];
                    storage[i + 1] = temp;
                }
            }
        }
    }
}

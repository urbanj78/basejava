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

    private void toSwap(int first, int second){ //метод меняет местами пару чисел массива
        Resume temp = storage[first];      //во временную переменную помещаем первый элемент
        storage[first] = storage[second];       //на место первого ставим второй элемент
        storage[second] = temp;          //вместо второго элемента пишем первый из временной памяти
    }

    public void bubbleSorter(){     //МЕТОД ПУЗЫРЬКОВОЙ СОРТИРОВКИ
        for (int i1 = size(); i1 >= 0; i1--){  //Внешний цикл
            for (int i2 = 0; i2 < i1; i2++){       //Внутренний цикл
                if(storage[i2] == null && storage[i1] != null)          //Если порядок элементов нарушен
                    toSwap(i2, i2 + 1);             //вызвать метод, меняющий местами
            }
        }
    }
}

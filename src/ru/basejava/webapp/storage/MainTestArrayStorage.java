package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

/**
 * Test for your ru.basejava.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final MapUUIDStorage ARRAY_STORAGE = new MapUUIDStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "John Dow");
        Resume r2 = new Resume("uuid2", "Василий Тёркин");
        Resume r3 = new Resume("uuid3", "Prime Optimus");
        Resume r4 = new Resume("uuid4", "Пётр Пустота");

        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r1);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        ARRAY_STORAGE.update(r3);

        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
            System.out.println(r.getFullName());
        }
    }
}

package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.basejava.webapp.storage.serializing.Serializing.O_S_SERIALIZER;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;

    protected FileStorage(String path) {
        File dir = new File(path);
        Objects.requireNonNull(dir, "directory must not be null");
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir.getAbsolutePath() + " is not directory");
        }
        if (!dir.canRead() || !dir.canWrite()) {
            throw new IllegalArgumentException(dir.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = dir;
    }

    @Override
    protected void doUpdate(Resume r, File searchKey) {
        try {
            O_S_SERIALIZER.doWrite(r, new BufferedOutputStream(new FileOutputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Resume r, File searchKey) {
        try {
            searchKey.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + searchKey.getAbsolutePath(), searchKey.getName(), e);
        }
        doUpdate(r, searchKey);
    }

    @Override
    protected Resume doGet(File searchKey) {
        try {
            return O_S_SERIALIZER.doRead(new BufferedInputStream(new FileInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("File read error", searchKey.getName(), e);
        }
    }

    @Override
    protected void doDelete(File searchKey) {
        if (!searchKey.delete()) {
            throw new StorageException("File delete error", searchKey.getName());
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    @Override
    protected List<Resume> doGetAll() {
        File[] files = storageToArray();
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        for (File file : storageToArray()) {
            doDelete(file);
        }
    }

    @Override
    public int size() throws IOException {
        return storageToArray().length;
    }

    private File[] storageToArray() {
        File[] list = directory.listFiles();
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list;
    }
}

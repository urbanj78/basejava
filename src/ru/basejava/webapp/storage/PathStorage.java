package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.basejava.webapp.storage.serializing.Serializing.O_S_SERIALIZER;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected PathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected void doUpdate(Resume r, Path searchKey) {
        try {
            O_S_SERIALIZER.doWrite(r, new BufferedOutputStream(Files.newOutputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Path write error ", r.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path searchKey) {
        try {
            Files.createFile(searchKey);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " + searchKey, r.getUuid(), e);
        }
        doUpdate(r, searchKey);
    }

    @Override
    protected Resume doGet(Path searchKey) {
        try {
            return O_S_SERIALIZER.doRead(new BufferedInputStream(Files.newInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Path read error", searchKey.toString(), e);
        }
    }

    @Override
    protected void doDelete(Path searchKey) {
        try {
            Files.delete(searchKey);
        } catch (IOException e) {
            throw new StorageException("Path delete error", searchKey.toString());
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path searchKey) {
        return Files.exists(searchKey);
    }

    @Override
    protected List<Resume> doGetAll() {
        return storageToList().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        storageToList().forEach(this::doDelete);
    }

    @Override
    public int size() throws IOException {
        return (int) storageToList().count();
    }

    private Stream<Path> storageToList(){
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Path read error", directory.toString(), e);
        }
    }
}

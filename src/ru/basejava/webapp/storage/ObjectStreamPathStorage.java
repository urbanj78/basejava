package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class ObjectStreamPathStorage extends AbstractPathStorage{
    protected ObjectStreamPathStorage(Path directory) {
        super(directory);
    }
    @Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {

    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        return null;
    }
}

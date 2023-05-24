package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Serializing {
    void doWrite(Resume r, OutputStream os) throws IOException;

    Resume doRead(InputStream is) throws IOException;
}

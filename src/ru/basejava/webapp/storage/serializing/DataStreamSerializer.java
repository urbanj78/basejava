package ru.basejava.webapp.storage.serializing;

import ru.basejava.webapp.model.*;

import java.io.*;
import java.time.Month;
import java.util.*;

public class DataStreamSerializer implements Serializing {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            writeWithException(dos, contacts.entrySet(), contact -> {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            });

            Map<SectionType, Section> sections = r.getSections();
            writeWithException(dos, sections.entrySet(), sect -> {
                SectionType sectionType = sect.getKey();
                Section section = sect.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATIONS ->
                            writeWithException(dos, ((ListSection) section).getList(), dos::writeUTF);
                    case EDUCATION, EXPERIENCE -> companyWrite(dos, ((CompanySection) section).getCompanies());
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, sectionRead(sectionType, dis));
            }
            return resume;
        }
    }

    private Section sectionRead(SectionType sectionType, DataInputStream dis) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> {
                return new TextSection(dis.readUTF());
            }
            case ACHIEVEMENT, QUALIFICATIONS -> {
                return new ListSection(listReadWithException(dis, dis::readUTF));
            }
            case EDUCATION, EXPERIENCE -> {
                return new CompanySection(listReadWithException(dis, () -> new Company(new Link(dis.readUTF(), dis.readUTF()), listReadWithException(dis, () -> new Company.Period(dis.readInt(), Month.of(dis.readInt()), dis.readUTF(), dis.readUTF())))));
            }
            default -> throw new IllegalStateException();
        }
    }

    private void companyWrite(DataOutputStream dos, List<Company> section) throws IOException {
        writeWithException(dos, section, com -> {
            dos.writeUTF(com.getWebsite().getName());
            String linkValue = com.getWebsite().getLink();
            dos.writeUTF(linkValue == null ? "" : linkValue);
            dos.writeInt(com.getPeriods().size());
            writeWithException(dos, com.getPeriods(), period -> {
                dos.writeInt(period.getStartDate().getYear());
                dos.writeInt(period.getEndDate().getMonth().getValue());
                dos.writeUTF(period.getTitle());
                String descriptionValue = period.getDescription();
                dos.writeUTF(descriptionValue == null ? "" : descriptionValue);
            });
        });
    }

    private <T> List<T> listReadWithException(DataInputStream dis, SymbolReader<T> reader) throws IOException {
        int listSize = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private void readWithException(DataInputStream dis, BlockAction action) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            action.action();
        }
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, SymbolWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    @FunctionalInterface
    private interface BlockAction {
        void action() throws IOException;
    }

    @FunctionalInterface
    private interface SymbolReader<T> {
        T read() throws IOException;
    }

    @FunctionalInterface
    private interface SymbolWriter<T> {
        void write(T t) throws IOException;
    }
}
package ru.basejava.webapp.storage.serializing;

import ru.basejava.webapp.model.*;

import java.io.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializing {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                sectionWrite(sectionType, section, dos);
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
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

    private void sectionWrite(SectionType sectionType, Section section, DataOutputStream dos) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getText());
            case ACHIEVEMENT, QUALIFICATIONS -> listWrite(dos, ((ListSection) section).getList());
            case EDUCATION, EXPERIENCE -> companyWrite(dos, ((CompanySection) section).getCompanies());
        }
    }

    private Section sectionRead(SectionType sectionType, DataInputStream dis) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> {
                return new TextSection(dis.readUTF());
            }
            case ACHIEVEMENT, QUALIFICATIONS -> {
                return new ListSection(listRead(dis));
            }
            case EDUCATION, EXPERIENCE -> {
                return new CompanySection(companyRead(dis));
            }
            default -> throw new IllegalStateException();
        }
    }

    private List<String> listRead(DataInputStream dis) throws IOException {
        int listSize = dis.readInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(dis.readUTF());
        }
        return list;
    }

    private List<Company> companyRead(DataInputStream dis) throws IOException {
        List<Company> companyList = new ArrayList<>();
        int companyCount = dis.readInt();
        for (int i = 0; i < companyCount; i++) {
            Link url = new Link(dis.readUTF(), dis.readUTF());
            int periodCount = dis.readInt();
            List<Company.Period> periodList = new ArrayList<>();
            for (int j = 0; j < periodCount; j++) {
                periodList.add(new Company.Period(dis.readInt(), Month.of(dis.readInt()), dis.readUTF(), dis.readUTF()));
            }
            companyList.add(new Company(url, periodList));
        }
        return companyList;
    }

    private void listWrite(DataOutputStream dos, List<String> list) throws IOException {
        dos.writeInt(list.size());
        for (String text : list) {
            dos.writeInt(text.length());
            dos.writeUTF(text);
        }
    }

    private void companyWrite(DataOutputStream dos, List<Company> section) throws IOException {
        writeWithExeption(dos, section, com -> {
            dos.writeUTF(com.getWebsite().getName());
            String linkValue = com.getWebsite().getLink();
            dos.writeUTF(linkValue == null ? "" : linkValue);
            dos.writeInt(com.getPeriods().size());
            writeWithExeption (dos, com.getPeriods(), period -> {
                dos.writeInt(period.getStartDate().getYear());
                dos.writeInt(period.getEndDate().getMonth().getValue());
                dos.writeUTF(period.getTitle());
                String descriptionValue = period.getDescription();
                dos.writeUTF(descriptionValue == null ? "" : descriptionValue);
            });
        });
    }

    private <T> void writeWithExeption(DataOutputStream dos, Collection<T> collection, symbolWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    @FunctionalInterface
    private interface symbolWriter<T> {
        void write(T t) throws IOException;
    }
}
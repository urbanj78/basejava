package ru.basejava.webapp.storage.serializing;

import ru.basejava.webapp.model.*;

import java.io.*;
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

            for (Map.Entry<SectionType, Section> entry : r.getSections().entrySet()) {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATIONS -> listWrite(dos, ((ListSection) section).getList());
                    case EDUCATION, EXPERIENCE -> companyWrite(dos, ((CompanySection) section).getCompanies());
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            return resume;
        }
    }

    private void listWrite(DataOutputStream dos, List<String> list) throws IOException {
        dos.writeInt(list.size());
        for (String text : list) {
            dos.writeUTF(text);
        }
    }

    private void companyWrite(DataOutputStream dos, List<Company> section) throws IOException {
        dos.writeInt(section.size());
        for (Company com : section) {
            dos.writeUTF(com.getWebsite().getName());
            dos.writeUTF(com.getWebsite().getLink());
            for (Company.Period period : com.getPeriods()) {
                dos.writeInt(period.getStartDate().getYear());
                dos.writeInt(period.getEndDate().getMonth().getValue());
                dos.writeUTF(period.getTitle());
                dos.writeUTF(period.getDescription());
            }
        }
    }
}
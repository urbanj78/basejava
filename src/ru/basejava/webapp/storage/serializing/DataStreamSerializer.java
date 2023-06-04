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
            Map<SectionType, Section> sections = r.getSections();
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().getTitle());
                if (entry instanceof TextSection) {
                    dos.writeUTF(entry.getValue().toString());
                } else if (entry instanceof ListSection) {
                    List<String> list = ((ListSection) entry).getList();
                    for (String s : list) {
                        dos.writeUTF(s);
                    }
                } else if (entry instanceof CompanySection) {
                    List<Company> companies = ((CompanySection) entry).getCompanies();
                    for (Company company : companies){
                        dos.writeUTF(company.getWebsite().getName());
                        dos.writeUTF(company.getWebsite().getLink());
                        List<Company.Period> periods = company.getPeriods();
                        for (Company.Period period : periods) {
                            dos.writeUTF(period.getStartDate().toString());
                            dos.writeUTF(period.getEndDate().toString());
                            dos.writeUTF(period.getTitle());
                            dos.writeUTF(period.getDescription());
                        }
                    }
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
}
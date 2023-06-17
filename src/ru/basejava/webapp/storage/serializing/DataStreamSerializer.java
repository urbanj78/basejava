package ru.basejava.webapp.storage.serializing;

import ru.basejava.webapp.model.*;

import java.io.*;
import java.time.Month;
import java.util.ArrayList;
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
            resume.addSection(SectionType.PERSONAL, new TextSection(dis.readUTF()));
            resume.addSection(SectionType.OBJECTIVE, new TextSection(dis.readUTF()));
            resume.addSection(SectionType.ACHIEVEMENT, new ListSection(listRead(dis)));
            resume.addSection(SectionType.QUALIFICATIONS, new ListSection(listRead(dis)));
            resume.addSection(SectionType.EDUCATION, new CompanySection(companyRead(dis)));
            resume.addSection(SectionType.EXPERIENCE, new CompanySection(companyRead(dis)));
            return resume;
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
        dos.writeInt(section.size());
        for (Company com : section) {
            dos.writeUTF(com.getWebsite().getName());
            dos.writeUTF(com.getWebsite().getLink());
            dos.writeInt(com.getPeriods().size());
            for (Company.Period period : com.getPeriods()) {
                dos.writeInt(period.getStartDate().getYear());
                dos.writeInt(period.getEndDate().getMonth().getValue());
                dos.writeUTF(period.getTitle());
                dos.writeUTF(period.getDescription());
            }
        }
    }
}
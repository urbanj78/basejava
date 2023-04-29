package ru.basejava.webapp.model;

import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

        Resume resume = new Resume("uuid1", "Григорий Кислин");
        contacts.put(ContactType.TEL_NUMBER, "+7(921) 855-0482)");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.HTTP_LINK1, new HTTP_Link("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin").toString());
        contacts.put(ContactType.HTTP_LINK2, new HTTP_Link("Профиль GitHub", "https://github.com/gkislin").toString());
        contacts.put(ContactType.HTTP_LINK3, new HTTP_Link("Профиль Stackoverflow", "https://stackoverflow.com/users/548473").toString());
        contacts.put(ContactType.HTTP_LINK4, new HTTP_Link("Домашняя страница", "http://gkislin.ru/").toString());
    }
}

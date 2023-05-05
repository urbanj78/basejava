package ru.basejava.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

        Resume resume = new Resume("uuid1", "Григорий Кислин");

        contacts.put(ContactType.TEL_NUMBER, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.HTTP_LINK1, new Link("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin").toString());
        contacts.put(ContactType.HTTP_LINK2, new Link("Профиль GitHub", "https://github.com/gkislin").toString());
        contacts.put(ContactType.HTTP_LINK3, new Link("Профиль Stackoverflow", "https://stackoverflow.com/users/548473").toString());
        contacts.put(ContactType.HTTP_LINK4, new Link("Домашняя страница", "http://gkislin.ru/").toString());

        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.ACHIEVEMENT, new ListSection(new ArrayList<>(Arrays.asList("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет", "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.", "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.", "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.", "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).", "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."))));
        sections.put(SectionType.QUALIFICATIONS, new ListSection(new ArrayList<>(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2", "Version control: Subversion, Git, Mercury, ClearCase, Perforce", "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB", "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy", "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts", "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).", "Python: Django.", "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js", "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka", "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.", "Инструменты: Maven + plugin development, Gradle, настройка Ngnix", "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer", "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования", "Родной русский, английский \"upper intermediate\""))));

        Link AlkatelLink = new Link("Alcatel", "http://www.alcatel.ru/");

        //Company JOProjects = new Company(new Link("Java Online Projects", "http://javaops.ru/"), new ArrayList<>(List.of(new Period("10/2013", "Сейчас", "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."))));
        //Company Wrike = new Company(new Link("Wrike", "https://www.wrike.com/"), new ArrayList<>(List.of(new Period(LocalDate.of(2014, 10, 01), LocalDate.of(2016, 01, 01), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))));
        //Company RITCenter = new Company(new Link("RIT Center", ""), new ArrayList<>(List.of(new Period("04/2012", "10/2014", "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"))));
        //Company LuxoftW = new Company(new Link("Luxoft", "http://www.luxoft.ru/"), new ArrayList<>(List.of(new Period("12/2010", "04/2012", "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."))));
        //Company Yota = new Company(new Link("Yota", "https://www.yota.ru/"), new ArrayList<>(List.of(new Period("06/2008", "12/2010", "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"))));
        //Company Enkata = new Company(new Link("Enkata", "http://enkata.com/"), new ArrayList<>(List.of(new Period("03/2007", "06/2008", "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."))));
        //Company SiemensW = new Company(new Link("Siemens AG", "https://www.siemens.com/ru/ru/home.html"), new ArrayList<>(List.of(new Period("01/2005", "02/2007", "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."))));
        //Company AlcatelW = new Company(AlkatelLink, new ArrayList<>(List.of(new Period("09/1997", "01/2005", "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))));

        //sections.put(SectionType.EXPERIENCE, new CompanySection(new ArrayList<>(Arrays.asList(JOProjects, Wrike, RITCenter, LuxoftW, Yota, Enkata, SiemensW, AlcatelW))));

        //Company Coursera = new Company(new Link("Coursera", "https://www.coursera.org/course/progfun"), new ArrayList<>(List.of(new Period("03/2013", "05/2013", "'Functional Programming Principles in Scala' by Martin Odersky"))));
        //Company LuxoftE = new Company(new Link("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"), new ArrayList<>(List.of(new Period("03/2011", "04/2011", "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'"))));
        //Company SiemensE = new Company(new Link("Siemens AG", "https://www.siemens.ru"), new ArrayList<>(List.of(new Period("01/2005", "04/2005", "3 месяца обучения мобильным IN сетям (Берлин)"))));
        //Company AlcatelE = new Company(AlkatelLink, new ArrayList<>(List.of(new Period("09/1997", "03/1998", "6 месяцев обучения цифровым телефонным сетям (Москва)"))));
        //Company IFMO = new Company(new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"), new ArrayList<>(Arrays.asList(new Period("09/1993", "07/1996", "Аспирантура (программист С, С++)", ""), new Period("09/1987", "07/1993", "Инженер (программист Fortran, C)"))));
        //Company MIPT = new Company(new Link("Заочная физико-техническая школа при МФТИ", "https://mipt.ru/"), new ArrayList<>(List.of(new Period("09/1984", "06/1987", "Закончил с отличием"))));

        //sections.put(SectionType.EDUCATION, new CompanySection(new ArrayList<>(Arrays.asList(Coursera, LuxoftE, SiemensE, AlcatelE, IFMO, MIPT))));

        System.out.println(resume.getFullName());

        ContactType[] contactTypes = ContactType.values();

        for (ContactType contactType : contactTypes) {
            System.out.print(contactType.getTitle());
            System.out.println(contacts.get(contactType));
        }

        SectionType[] sectionTypes = SectionType.values();

        for (SectionType sectionType : sectionTypes) {
            System.out.println(sectionType.getTitle());
        //    System.out.println(sections.get(sectionType).sectionForPrint());
        }
    }
}

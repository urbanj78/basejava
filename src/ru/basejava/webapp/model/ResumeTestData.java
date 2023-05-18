package ru.basejava.webapp.model;

import ru.basejava.webapp.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static ru.basejava.webapp.util.DateUtil.NOW;

public class ResumeTestData {
    public static Resume fillResume(String uuid, String fullName) {
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

        Resume resume = new Resume(uuid, fullName);

        contacts.put(ContactType.TEL_NUMBER, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, new Link("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin").toString());
        contacts.put(ContactType.GITHUB, new Link("Профиль GitHub", "https://github.com/gkislin").toString());
        contacts.put(ContactType.STACKOVERFLOW, new Link("Профиль Stackoverflow", "https://stackoverflow.com/users/548473").toString());
        contacts.put(ContactType.HOME_PAGE, new Link("Домашняя страница", "http://gkislin.ru/").toString());

        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.ACHIEVEMENT, new ListSection(new ArrayList<>(Arrays.asList("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет", "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.", "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.", "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.", "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).", "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."))));
        sections.put(SectionType.QUALIFICATIONS, new ListSection(new ArrayList<>(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2", "Version control: Subversion, Git, Mercury, ClearCase, Perforce", "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB", "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy", "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts", "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).", "Python: Django.", "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js", "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka", "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.", "Инструменты: Maven + plugin development, Gradle, настройка Ngnix", "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer", "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования", "Родной русский, английский \"upper intermediate\""))));

        Link AlkatelLink = new Link("Alcatel", "http://www.alcatel.ru/");

        Company JOProjects = new Company(new Link("Java Online Projects", "http://javaops.ru/"), new ArrayList<>(List.of(new Company.Period(DateUtil.of(2013, Month.of(10)), NOW, "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."))));
        Company Wrike = new Company(new Link("Wrike", "https://www.wrike.com/"), new ArrayList<>(List.of(new Company.Period(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))));
        Company RITCenter = new Company(new Link("RIT Center", ""), new ArrayList<>(List.of(new Company.Period(DateUtil.of(2012, Month.of(4)), DateUtil.of(2014, Month.of(10)), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"))));
        Company LuxoftW = new Company(new Link("Luxoft", "http://www.luxoft.ru/"), new ArrayList<>(List.of(new Company.Period(DateUtil.of(2010, Month.of(12)), DateUtil.of(2012, Month.of(4)), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."))));
        Company Yota = new Company(new Link("Yota", "https://www.yota.ru/"), new ArrayList<>(List.of(new Company.Period(DateUtil.of(2008, Month.of(6)), DateUtil.of(2010, Month.of(12)), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"))));
        Company Enkata = new Company(new Link("Enkata", "http://enkata.com/"), new ArrayList<>(List.of(new Company.Period(DateUtil.of(2007, Month.of(3)), DateUtil.of(2008, Month.of(6)), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."))));
        Company SiemensW = new Company(new Link("Siemens AG", "https://www.siemens.com/ru/ru/home.html"), new ArrayList<>(List.of(new Company.Period(DateUtil.of(2005, Month.of(1)), DateUtil.of(2007, Month.of(2)), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."))));
        Company AlcatelW = new Company(AlkatelLink, new ArrayList<>(List.of(new Company.Period(DateUtil.of(1997, Month.of(9)), DateUtil.of(2005, Month.of(1)), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))));

        sections.put(SectionType.EXPERIENCE, new CompanySection(new ArrayList<>(Arrays.asList(JOProjects, Wrike, RITCenter, LuxoftW, Yota, Enkata, SiemensW, AlcatelW))));

        Company Coursera = new Company(new Link("Coursera", "https://www.coursera.org/course/progfun"), new ArrayList<>(List.of(new Company.Period(DateUtil.of(2013, Month.of(3)), DateUtil.of(2013, Month.of(5)), "'Functional Programming Principles in Scala' by Martin Odersky", null))));
        Company LuxoftE = new Company(new Link("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"), new ArrayList<>(List.of(new Company.Period(DateUtil.of(2011, Month.of(3)), DateUtil.of(2011, Month.of(4)), "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", null))));
        Company SiemensE = new Company(new Link("Siemens AG", "https://www.siemens.ru"), new ArrayList<>(List.of(new Company.Period(DateUtil.of(2005, Month.of(1)), DateUtil.of(2005, Month.of(4)), "3 месяца обучения мобильным IN сетям (Берлин)", null))));
        Company AlcatelE = new Company(AlkatelLink, new ArrayList<>(List.of(new Company.Period(DateUtil.of(1997, Month.of(8)), DateUtil.of(1998, Month.of(3)), "6 месяцев обучения цифровым телефонным сетям (Москва)", null))));
        Company IFMO = new Company(new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"), new ArrayList<>(Arrays.asList(new Company.Period(DateUtil.of(1993, Month.of(9)), DateUtil.of(1996, Month.of(7)), "Аспирантура (программист С, С++)", null), new Company.Period(DateUtil.of(1987, Month.of(9)), DateUtil.of(1993, Month.of(7)), "Инженер (программист Fortran, C)", null))));
        Company MIPT = new Company(new Link("Заочная физико-техническая школа при МФТИ", "https://mipt.ru/"), new ArrayList<>(List.of(new Company.Period(DateUtil.of(1984, Month.of(9)), DateUtil.of(1987, Month.of(6)), "Закончил с отличием", null))));

        sections.put(SectionType.EDUCATION, new CompanySection(new ArrayList<>(Arrays.asList(Coursera, LuxoftE, SiemensE, AlcatelE, IFMO, MIPT))));

        return resume;
    }
}



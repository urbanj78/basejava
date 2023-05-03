package ru.basejava.webapp.model;

public enum ContactType {
    TEL_NUMBER("Тел.:"),
    SKYPE("Skype:"),
    EMAIL("Почта:"),
    HTTP_LINK1("Профиль LinkedIn"),
    HTTP_LINK2("Профиль GitHub"),
    HTTP_LINK3("Профиль Stackoverflow"),
    HTTP_LINK4("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

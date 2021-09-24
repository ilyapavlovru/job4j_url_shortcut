package ru.job4j.url_shortcut.domain;

import ru.job4j.url_shortcut.util.PasswordGenerator;

import java.util.Objects;

public class Person {
    private String username;
    private String password;
    private Site site;
    private boolean registration;

    public Person() {
        PasswordGenerator loginGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(false)
                .useLower(true)
                .useUpper(true)
                .build();
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        this.username = loginGenerator.generate(6);
        this.password = passwordGenerator.generate(10);
        this.registration = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(username, person.username) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}

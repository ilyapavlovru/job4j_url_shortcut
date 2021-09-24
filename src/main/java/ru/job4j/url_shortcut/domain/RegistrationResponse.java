package ru.job4j.url_shortcut.domain;

public class RegistrationResponse {

    private boolean registration;
    private String login;
    private String password;

    public RegistrationResponse(boolean registration, String username, String password) {
        this.registration = registration;
        this.login = username;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package ru.job4j.url_shortcut.domain;

public class RegistrationResponse {

    private boolean registration;
    private String username;
    private String password;

    public RegistrationResponse(boolean registration, String username, String password) {
        this.registration = registration;
        this.username = username;
        this.password = password;
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
}

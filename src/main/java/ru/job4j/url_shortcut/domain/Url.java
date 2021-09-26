package ru.job4j.url_shortcut.domain;

public class Url {

    private String value;
    private int total;

    public Url(String url, int total) {
        this.value = url;
        this.total = total;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

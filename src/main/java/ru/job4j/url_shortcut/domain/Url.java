package ru.job4j.url_shortcut.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;
    private String code;
    private int total;

    public Url() {

    }

    public Url(String url, int total) {
        this.value = url;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return id == url.id &&
                total == url.total &&
                Objects.equals(value, url.value) &&
                Objects.equals(code, url.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, code, total);
    }
}

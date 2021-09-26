package ru.job4j.url_shortcut.domain;

public class ConvertResponse {

    private String code;

    public ConvertResponse(String convertedUrl) {
        this.code = convertedUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

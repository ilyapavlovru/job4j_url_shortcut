package ru.job4j.url_shortcut.store;

import org.springframework.stereotype.Component;
import ru.job4j.url_shortcut.util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UrlStore {

    // хранилище (ключ, значение) = (уникальный код сайта, url сайта)
    private final ConcurrentHashMap<String, String> urls = new ConcurrentHashMap<>();

    public String save(String url) {
        PasswordGenerator codeGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String convertedUrl = codeGenerator.generate(6);
        urls.put(convertedUrl, url);
        return convertedUrl;
    }

    // возвращает url сайта по коду
    public String findByCode(String code) {
        return urls.get(code);
    }

    public List<String> findAll() {
        return new ArrayList<>(urls.values());
    }
}

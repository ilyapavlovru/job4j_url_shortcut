package ru.job4j.url_shortcut.store;

import org.springframework.stereotype.Repository;
import ru.job4j.url_shortcut.domain.Url;
import ru.job4j.url_shortcut.util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlMemRepository {

    private final ConcurrentHashMap<String, Url> urls = new ConcurrentHashMap<>();

    public String save(String url) {
        PasswordGenerator codeGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String convertedUrl = codeGenerator.generate(6);

        Url createdUrl = new Url(url, 0);

        urls.put(convertedUrl, createdUrl);
        return convertedUrl;
    }

    public Url findByCode(String code) {
        return urls.get(code);
    }

    public List<Url> findAll() {
        return new ArrayList<>(urls.values());
    }

    public Optional<Url> findByValue(String value) {
        List<Url> list = new ArrayList<>(urls.values());
        return list.stream()
                .filter(url -> url.getValue().equals(value))
                .findFirst();
    }
}

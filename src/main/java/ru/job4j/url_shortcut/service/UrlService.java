package ru.job4j.url_shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.domain.Url;
import ru.job4j.url_shortcut.store.UrlRepository;
import ru.job4j.url_shortcut.util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url save(String url) {

        PasswordGenerator codeGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String convertedUrl = codeGenerator.generate(6);
        Url createdUrl = new Url(url, 0);
        createdUrl.setCode(convertedUrl);

        return urlRepository.save(createdUrl);
    }

    public Url findByCode(String code) {
        return urlRepository.findByCode(code);
    }

    public List<Url> findAll() {
        List<Url> rsl = new ArrayList<>();
        urlRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public void updateUrlStatistics(String code) {
        Url foundUrl = urlRepository.findByCode(code);
        foundUrl.setTotal(foundUrl.getTotal() + 1);
        urlRepository.save(foundUrl);
    }

    public Url findByValue(String value) {
        return urlRepository.findByValue(value);
    }
}
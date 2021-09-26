package ru.job4j.url_shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.domain.Url;
import ru.job4j.url_shortcut.store.UrlRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String save(String url) {
        return urlRepository.save(url);
    }

    public Url findByCode(String code) {
        return urlRepository.findByCode(code);
    }

    public List<Url> findAll() {
        return urlRepository.findAll();
    }

    public void updateUrlStatistics(String code) {
        Url foundUrl = urlRepository.findByCode(code);
        foundUrl.setTotal(foundUrl.getTotal() + 1);
    }

    public Optional<Url> findByValue(String value) {
        return urlRepository.findByValue(value);
    }
}

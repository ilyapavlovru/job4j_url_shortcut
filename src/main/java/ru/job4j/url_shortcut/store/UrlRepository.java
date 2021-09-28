package ru.job4j.url_shortcut.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.domain.Url;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    Url findByValue(String value);
    Url findByCode(String code);
}

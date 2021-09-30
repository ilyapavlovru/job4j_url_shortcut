package ru.job4j.url_shortcut.store;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url_shortcut.domain.Url;

@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {
    Url findByValue(String value);
    Url findByCode(String code);

    @Transactional
    @Modifying
    @Query("UPDATE Url SET total = total + 1 WHERE code = :code")
    void updateUrlStatistics(@Param("code") String code);
}

package ru.job4j.url_shortcut.store;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url_shortcut.domain.Person;

@Repository
public interface UserRepository extends CrudRepository<Person, Integer> {
    Person findByUsername(String username);
    Person findBySite(String site);
}

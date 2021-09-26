package ru.job4j.url_shortcut.store;

import org.springframework.stereotype.Component;
import ru.job4j.url_shortcut.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStore {

    private final ConcurrentHashMap<String, Person> users = new ConcurrentHashMap<>();

    public void save(Person person) {
        users.put(person.getUsername(), person);
    }

    public Person findByUsername(String username) {
        return users.get(username);
    }

    public Optional<Person> findBySite(String site) {
        List<Person> list = new ArrayList<>(users.values());
        return list.stream()
                .filter(person -> person.getSite().getSite().equals(site))
                .findFirst();
    }

    public List<Person> findAll() {
        return new ArrayList<>(users.values());
    }
}

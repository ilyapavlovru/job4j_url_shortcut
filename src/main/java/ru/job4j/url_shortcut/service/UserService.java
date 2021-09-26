package ru.job4j.url_shortcut.service;


import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.domain.Person;
import ru.job4j.url_shortcut.store.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(Person person) {
        userRepository.save(person);
    }

    public Person findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<Person> findBySite(String site) {
        return userRepository.findBySite(site);
    }

    public List<Person> findAll() {
        return userRepository.findAll();
    }
}

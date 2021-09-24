package ru.job4j.url_shortcut.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.domain.Person;
import ru.job4j.url_shortcut.domain.RegistrationResponse;
import ru.job4j.url_shortcut.domain.Site;
import ru.job4j.url_shortcut.store.UserStore;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserStore users;
    private BCryptPasswordEncoder encoder;

    public UserController(UserStore users,
                          BCryptPasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @PostMapping("/registration")
    public RegistrationResponse registration(@RequestBody Site site) {
        Person person = new Person();
        person.setSite(site);
        RegistrationResponse registrationResponse = new RegistrationResponse(true, person.getUsername(), person.getPassword());
        person.setPassword(encoder.encode(person.getPassword()));
        users.save(person);
        return registrationResponse;
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return users.findAll();
    }
}
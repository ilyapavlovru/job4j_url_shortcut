package ru.job4j.url_shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url_shortcut.domain.Person;
import ru.job4j.url_shortcut.domain.RegistrationResponse;
import ru.job4j.url_shortcut.domain.Site;
import ru.job4j.url_shortcut.store.UserStore;

import java.util.List;
import java.util.Optional;

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

        Optional<Person> foundBySitePerson = users.findBySite(site.getSite());
        if (foundBySitePerson.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Site with the same name is found. Please, use unique site!"
            );
        }

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
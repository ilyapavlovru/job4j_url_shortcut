package ru.job4j.url_shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url_shortcut.domain.Person;
import ru.job4j.url_shortcut.domain.RegistrationResponse;
import ru.job4j.url_shortcut.domain.SiteRequest;
import ru.job4j.url_shortcut.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private BCryptPasswordEncoder encoder;

    public UserController(UserService userService,
                          BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/registration")
    public RegistrationResponse registration(@RequestBody SiteRequest siteRequest) {

        Person foundBySitePerson = userService.findBySite(siteRequest.getSite());
        if (foundBySitePerson != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "SiteRequest with the same name is found. Please, use unique siteRequest!"
            );
        }

        Person person = new Person();
        person.setSite(siteRequest.getSite());
        RegistrationResponse registrationResponse = new RegistrationResponse(true, person.getUsername(), person.getPassword());
        person.setPassword(encoder.encode(person.getPassword()));
        userService.save(person);
        return registrationResponse;
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return StreamSupport.stream(
                this.userService.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }
}
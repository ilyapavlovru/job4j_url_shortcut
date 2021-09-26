package ru.job4j.url_shortcut.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url_shortcut.domain.ConvertRequest;
import ru.job4j.url_shortcut.domain.ConvertResponse;
import ru.job4j.url_shortcut.domain.Person;
import ru.job4j.url_shortcut.domain.Url;
import ru.job4j.url_shortcut.service.UrlService;
import ru.job4j.url_shortcut.service.UserService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
public class ProcessController {

    private UserService userService;
    private UrlService urlService;

    public ProcessController(UserService userService, UrlService urlService) {
        this.userService = userService;
        this.urlService = urlService;
    }

    @PostMapping("/convert")
    public ResponseEntity<ConvertResponse> convert(@RequestBody ConvertRequest request) throws MalformedURLException {
        String url = request.getUrl();
        String host = new URL(url).getHost();
        Optional<Person> foundBySitePerson = userService.findBySite(host);
        if (foundBySitePerson.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Host of requested url is not registered!"
            );
        }
        Optional<Url> foundUrl = urlService.findByValue(url);
        if (foundUrl.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Url with the same name is found. Please, use unique url!"
            );
        }
        String convertedUrl = urlService.save(url);
        return new ResponseEntity<>(new ConvertResponse(convertedUrl), HttpStatus.OK);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {
        Url url = urlService.findByCode(code);
        if (url == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Unknown site code!"
            );
        }
        urlService.updateUrlStatistics(code);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", url.getValue());
        return new ResponseEntity<>(headers, HttpStatus.MOVED_TEMPORARILY);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<Url>> getStatistics() {
        List<Url> allUrls = urlService.findAll();
        return new ResponseEntity<>(allUrls, HttpStatus.OK);
    }
}

package ru.job4j.url_shortcut.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.domain.ConvertRequest;
import ru.job4j.url_shortcut.domain.ConvertResponse;
import ru.job4j.url_shortcut.store.UrlStore;

@RestController
public class ProcessController {

    private UrlStore urls;

    public ProcessController(UrlStore urls) {
        this.urls = urls;
    }

    @PostMapping("/convert")
    public ConvertResponse convert(@RequestBody ConvertRequest request) {

        String url = request.getUrl();

        String convertedUrl = urls.save(url);

        ConvertResponse convertResponse = new ConvertResponse();
        convertResponse.setCode(convertedUrl);

        return convertResponse;
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {

        String url = urls.findByCode(code);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", url);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_TEMPORARILY);

    }
}

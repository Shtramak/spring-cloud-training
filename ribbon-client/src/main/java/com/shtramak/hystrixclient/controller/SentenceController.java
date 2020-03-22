package com.shtramak.hystrixclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class SentenceController {
    private final RestTemplate restTemplate;

    public SentenceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getSentence() {
        return
                getWord("SUBJECT-SERVICE") + " "
                        + getWord("VERB-SERVICE") + " "
                        + getWord("ARTICLE-SERVICE") + " "
                        + getWord("ADJECTIVE-SERVICE") + " "
                        + getWord("NOUN-SERVICE") + "."
                ;
    }

    public String getWord(String service) {
        String url = String.format("http://%s", service);
        return restTemplate.getForObject(url, String.class);
    }
}

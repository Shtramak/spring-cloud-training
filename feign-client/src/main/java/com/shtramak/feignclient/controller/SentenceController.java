package com.shtramak.feignclient.controller;

import com.shtramak.feignclient.service.SentenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SentenceController {
    private final SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping
    public String getSentence() {
        return sentenceService.sentence();
    }
}

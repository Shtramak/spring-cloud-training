package com.shtramak.configserverclient.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@ConfigurationProperties(prefix = "word-config")
public class LuckyWordController {

    private String luckyWord;

    public String getLuckyWord() {
        return luckyWord;
    }

    public void setLuckyWord(String luckyWord) {
        this.luckyWord = luckyWord;
    }

    @GetMapping
    public String showLuckyWord() {
        return "The lucky word is: " + luckyWord;
    }
}

package com.shtramak.configserverclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LuckyWordController {

    @Value("${lucky-word}")
    String luckyWord;

    @GetMapping
    public String showLuckyWord() {
        return "The lucky word is: " + luckyWord;
    }
}

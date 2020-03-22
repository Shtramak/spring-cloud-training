package com.shtramak.hystrixclient.service;

import org.springframework.stereotype.Service;

@Service
public class SentenceServiceImpl implements SentenceService {
    private final WordService wordService;

    public SentenceServiceImpl(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public String sentence() {
        return String.format("%s %s %s %s %s",
                wordService.getSubject(),
                wordService.getVerb(),
                wordService.getArticle(),
                wordService.getAdjective(),
                wordService.getNoun());
    }
}

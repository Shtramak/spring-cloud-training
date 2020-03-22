package com.shtramak.hystrixclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shtramak.hystrixclient.dao.AdjectiveClient;
import com.shtramak.hystrixclient.dao.ArticleClient;
import com.shtramak.hystrixclient.dao.NounClient;
import com.shtramak.hystrixclient.dao.SubjectClient;
import com.shtramak.hystrixclient.dao.VerbClient;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService{
    private final VerbClient verbClient;
    private final SubjectClient subjectClient;
    private final ArticleClient articleClient;
    private final AdjectiveClient adjectiveClient;
    private final NounClient nounClient;

    public WordServiceImpl(VerbClient verbClient, SubjectClient subjectClient, ArticleClient articleClient, AdjectiveClient adjectiveClient, NounClient nounClient) {
        this.verbClient = verbClient;
        this.subjectClient = subjectClient;
        this.articleClient = articleClient;
        this.adjectiveClient = adjectiveClient;
        this.nounClient = nounClient;
    }

    @Override
    @HystrixCommand(fallbackMethod="getFallbackSubject")
    public String getSubject() {
        return subjectClient.getWord();
    }

    @Override
    public String getVerb() {
        return verbClient.getWord();
    }

    @Override
    public String getArticle() {
        return articleClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod="getFallbackAdjective")
    public String getAdjective() {
        return adjectiveClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod="getFallbackNoun")
    public String getNoun() {
        return nounClient.getWord();
    }

    public String getFallbackSubject() {
        return new String("Someone");
    }

    public String getFallbackAdjective() {
        return new String("");
    }

    public String getFallbackNoun() {
        return new String("something");
    }
}

package com.shtramak.feignclient.service;

import com.shtramak.feignclient.dao.NounClient;
import com.shtramak.feignclient.dao.AdjectiveClient;
import com.shtramak.feignclient.dao.ArticleClient;
import com.shtramak.feignclient.dao.SubjectClient;
import com.shtramak.feignclient.dao.VerbClient;
import org.springframework.stereotype.Service;

@Service
public class SentenceServiceImpl implements SentenceService {
    private final AdjectiveClient adjectiveClient;
    private final ArticleClient articleClient;
    private final NounClient nounClient;
    private final SubjectClient subjectClient;
    private final VerbClient verbClient;

    public SentenceServiceImpl(AdjectiveClient adjectiveClient, ArticleClient articleClient, NounClient nounClient, SubjectClient subjectClient, VerbClient verbClient) {
        this.adjectiveClient = adjectiveClient;
        this.articleClient = articleClient;
        this.nounClient = nounClient;
        this.subjectClient = subjectClient;
        this.verbClient = verbClient;
    }

    @Override
    public String sentence() {
        return String.format("%s %s %s %s %s",
                subjectClient.getWord(),
                verbClient.getWord(),
                articleClient.getWord(),
                adjectiveClient.getWord(),
                nounClient.getWord());
    }
}

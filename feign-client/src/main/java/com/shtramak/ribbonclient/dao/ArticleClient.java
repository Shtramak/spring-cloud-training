package com.shtramak.ribbonclient.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ARTICLE-SERVICE")
public interface ArticleClient {
    @GetMapping
    String getWord();
}

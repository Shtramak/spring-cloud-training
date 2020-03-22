package com.shtramak.feignclient.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("VERB-SERVICE")
public interface VerbClient {
    @GetMapping
    String getWord();
}

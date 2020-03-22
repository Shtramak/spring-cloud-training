package com.shtramak.feignclient.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ADJECTIVE-SERVICE")
public interface AdjectiveClient {
    @GetMapping
    String getWord();
}

package com.shtramak.hystrixclient.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("NOUN-SERVICE")
public interface NounClient {
    @GetMapping
    String getWord();
}

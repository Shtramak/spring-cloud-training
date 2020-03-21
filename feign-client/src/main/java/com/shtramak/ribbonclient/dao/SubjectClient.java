package com.shtramak.ribbonclient.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("SUBJECT-SERVICE")
public interface SubjectClient {
    @GetMapping
    String getWord();
}

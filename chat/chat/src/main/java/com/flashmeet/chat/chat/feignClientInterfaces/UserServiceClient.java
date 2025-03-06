package com.flashmeet.chat.chat.feignClientInterfaces;

import java.util.Map;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Response;

@FeignClient(name = "userService")

public interface UserServiceClient {

    @GetMapping(value = "/users/internal/get-user-by-id")
    Optional<ResponseEntity<String>> getUserById(@RequestParam String id);

}

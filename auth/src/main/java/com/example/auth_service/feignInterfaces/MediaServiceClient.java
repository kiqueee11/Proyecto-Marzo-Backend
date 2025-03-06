package com.example.auth_service.feignInterfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.auth_service.configuration.FeignEncoderConfig;

@FeignClient(name="mediaserver",configuration = FeignEncoderConfig.class)
public interface MediaServiceClient {

    @PostMapping(value = "/media/internal/uploadMedia",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadMedia(@RequestPart("file") MultipartFile file, @RequestParam("userId") String userId, @RequestParam("index") int index);

}

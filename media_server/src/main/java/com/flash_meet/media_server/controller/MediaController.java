package com.flash_meet.media_server.controller;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.flash_meet.media_server.services.MediaService;

@RestController
@RequestMapping("/media")
@CrossOrigin(origins = "*")
public class MediaController {

    private MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping(value = "/internal/uploadMedia")
    public String uploadMedia(@RequestPart("file") MultipartFile file, @RequestParam("userId") String userId,
            @RequestParam("index") int index) {
        try {
            if (file.isEmpty()) {
                return "File is empty";
            }

            String response = mediaService.uploadFile(file, userId, index);
            System.out.println(response);

            return response;
        } catch (Exception e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }

    @PostMapping(value = "/get-media")
    public byte[] getMedia(@RequestParam String fileName) {
        try {
            byte[] response = mediaService.getFile(fileName);

            if (response == null) {
                return null;
            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }
    }

}

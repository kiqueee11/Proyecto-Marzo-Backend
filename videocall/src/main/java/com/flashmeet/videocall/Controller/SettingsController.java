package com.flashmeet.videocall.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashmeet.videocall.model.VideoCallModel;
import com.flashmeet.videocall.service.VideoCallService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/VideoCall")
public class SettingsController {

    private VideoCallService videoCallService;

    public SettingsController(VideoCallService videoCallService) {
        this.videoCallService = videoCallService;
    }

    @PostMapping("/save")
    public ResponseEntity<VideoCallModel> saveVideoCall(@RequestBody VideoCallModel videoCallModel) {
        
        videoCallService.saveVideoCall(videoCallModel);
        return ResponseEntity.ok(videoCallModel);
    }

}
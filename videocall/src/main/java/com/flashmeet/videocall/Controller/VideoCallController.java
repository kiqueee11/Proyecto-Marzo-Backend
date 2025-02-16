package com.flashmeet.videocall.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashmeet.videocall.model.VideoCallModel;
import com.flashmeet.videocall.service.VideoCallService;

import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/videocall")
public class VideoCallController {
    
    private VideoCallService videoCallService;
    
    public VideoCallController(VideoCallService videoCallService) {
        this.videoCallService = videoCallService;
    }

    @PostMapping("save")
    public String postMethodName(@RequestBody VideoCallModel videoCallModel) {
        
        videoCallService.saveVideoCall(videoCallModel);
        return Response.ok(videoCallModel);
    }
    

}

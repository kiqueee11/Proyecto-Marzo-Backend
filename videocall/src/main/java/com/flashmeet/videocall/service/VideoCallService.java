package com.flashmeet.videocall.service;

import org.springframework.stereotype.Service;

import com.flashmeet.videocall.model.VideoCallModel;
import com.flashmeet.videocall.repository.VideoCallRepository;

@Service
public class VideoCallService{

    private VideoCallRepository videoCallRepository;

    public VideoCallService(VideoCallRepository videoCallRepository) {
        this.videoCallRepository = videoCallRepository;
    }

    public void saveVideoCall(VideoCallModel videoCallModel) {
        videoCallRepository.save(videoCallModel);
    }
    
}
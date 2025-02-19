package com.flashmeet.videocall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashmeet.videocall.model.VideoCallModel;

@Repository
public interface VideoCallRepository extends JpaRepository<VideoCallModel, Long>{

    

}
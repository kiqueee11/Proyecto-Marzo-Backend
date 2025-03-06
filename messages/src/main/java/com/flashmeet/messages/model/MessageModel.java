package com.flashmeet.messages.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String messageContent;

    @Column(nullable = false)
    private String senderId;

    @Column(nullable = false)
    private String chatId;

    @Column(nullable = false)
    
    private boolean isMessageRead;

    @Column(nullable = false)

    private String messageUID;


    @Version
    private Long version;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;



}

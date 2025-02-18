package com.flashmeet.chat.chat.model;

import java.sql.Date;

import javax.annotation.processing.SupportedSourceVersion;

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
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "chat")
public class ChatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String user1_id;
    @Column(nullable = false)

    private String user2_id;

    private String user1_name;

    private String user2_name;

    private String user1_picture;

    private String user2_picture;

    private boolean user1_online;

    private boolean user2_online;

    private boolean isChatAnnonimous=true;


    @Version
    private Long version;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;

}

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
    private String user1Id;
    @Column(nullable = false)

    private String user2Id;

    private String user1Name;

    private String user2Name;

    private String user1Picture;

    private String user2Picture;
    @Column(nullable = false)

    private boolean user1Online;
    @Column(nullable = false)

    private boolean user2Online;

    private boolean isChatAnnonimous = true;
    @Column(nullable = false)

    private boolean user1wantsToRevealIdentity;
    @Column(nullable = false)

    private boolean user2wantstoRevealIdentity;

    @Column(nullable = false)
    private String chatId;

    @Version
    private Long version;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;

}

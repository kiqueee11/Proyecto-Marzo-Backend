package com.example.auth_service.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Version;

@Entity
@Table(name = "user_auth_data")
@Data
public class UserAuthData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private long Id;
    @Column(length = 50, unique = false, nullable = false)
    private String name;
    @Column(length = 255, unique = true, nullable = false)
    private String password;

    @Column(unique = true, nullable = false,name = "user_id")
    private String userId;

    private String email;

    @Version
    private Long version;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;

}

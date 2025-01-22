package com.profiles.app.profile_manager_app.imported_models;

import java.time.LocalDateTime;  // Usar LocalDateTime en lugar de java.sql.Date

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
@Table(name = "user_auth_data")
@Data
public class UserAuthData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 255, unique = true, nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;  // Usar LocalDateTime

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;  // Usar LocalDateTime

    

}

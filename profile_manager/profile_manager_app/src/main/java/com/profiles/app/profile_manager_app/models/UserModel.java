package com.profiles.app.profile_manager_app.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.profiles.app.profile_manager_app.imported_models.UserAuthData;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;



@Data
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 15)
    private String userId;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_speaked_languages", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "language")
    @Size(max = 10, message = "A user can speak up to 10 languages")
    private List<@NotBlank String> speakedLanguages= new ArrayList<String>();
    
    @ElementCollection
    @CollectionTable(name = "user_learning_languages", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "language" )
    @Size(max = 10, message = "A user can learn up to 10 languages")
    private List<@NotBlank String> learningLanguages=new ArrayList<String>();

    @CollectionTable(name = "user_auth_data", joinColumns = @JoinColumn(name = "user_id"))
    private UserAuthData userAuthData;



    @Column(nullable = false)
    private boolean isUserActive;

    @Column(nullable = false)
    private boolean isUserBlocked;

    @Version
    private Long version;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;
}

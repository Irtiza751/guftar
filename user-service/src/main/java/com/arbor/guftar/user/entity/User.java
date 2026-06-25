package com.arbor.guftar.user.entity;

import com.arbor.guftar.user.types.AuthProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(columnDefinition = "TEXT")
    private String password;
    @Column(length = 20)
    private String phoneNo;
    @Column(columnDefinition = "TEXT")
    private String bio;
    private String imageUrl;
    private Boolean isVerifiedEmail = false;
    private Boolean isVerifiedPhone = false;
    private Boolean isPrivateAccount = false;
    @Enumerated(EnumType.STRING)
    private AuthProvider authMethod = AuthProvider.LOCAL;
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

}

package com.system.nizopay.core.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class User{
    private final String userId;
    private final String fullName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    public User(String userId,String fullName,String email,LocalDateTime createdAt,LocalDateTime updatedAt,LocalDateTime deletedAt){
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
    public static User create(String fullName,String email){
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        return new User(UUID.randomUUID().toString(),fullName,email,createdAt,updatedAt,null);
    }

    public static User restore(String userId, String fullName, String email, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt){
        return new User(userId,fullName,email,createdAt,updatedAt,deletedAt);
    }
}

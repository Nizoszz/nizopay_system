package com.system.nizopay.core.model;

import lombok.Getter;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Getter
public class User{
    private final String userId;
    private final String fullName;
    private final String email;
    private final Date createdAt;
    private final Date updatedAt;
    private final Optional<Date> deletedAt;

    public User(String userId,String fullName,String email,Date createdAt,Date updatedAt,Optional<Date> deletedAt){
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
    public static User create(String fullName,String email){
        Date createdAt = new Date();
        Date updatedAt = new Date();
        return new User(UUID.randomUUID().toString(),fullName,email,createdAt,updatedAt,Optional.empty());
    }

    public static User restore(final String userId, final String fullName, final String email, final Date createdAt, final Date updatedAt, final Date deletedAt){
        return new User(userId, fullName, email, createdAt, updatedAt, Optional.of(deletedAt));
    }
}

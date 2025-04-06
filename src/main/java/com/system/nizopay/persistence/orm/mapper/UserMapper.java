package com.system.nizopay.persistence.orm.mapper;

import com.system.nizopay.core.model.User;
import com.system.nizopay.persistence.orm.entity.UserEntity;

public class UserMapper{
    public static UserEntity toEntity(User user){
        return new UserEntity(user.getUserId(), user.getFullName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt());
    }
    public static User toModel(UserEntity userEntity){
        return new User(userEntity.getUserId(), userEntity.getFullName(), userEntity.getEmail(), userEntity.getCreatedAt(), userEntity.getUpdatedAt(), userEntity.getDeletedAt());
    }
}

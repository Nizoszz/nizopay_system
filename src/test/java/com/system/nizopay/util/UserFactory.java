package com.system.nizopay.util;

import com.system.nizopay.core.model.User;

public class UserFactory{
    public static User createUserToBeSaved(String fullName, String email){
        return User.create(fullName, email);
    }

    public static User createValidUser(){
        return User.create("UserTest", "usertest@mail.com");
    }
}

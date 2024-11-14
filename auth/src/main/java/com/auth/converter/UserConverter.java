package com.auth.converter;

import com.auth.bom.UserBom;
import com.auth.model.User;

public class UserConverter {

    public static UserBom convertToBom(User user) {
        UserBom userBom = new UserBom();
        userBom.setId(user.getId());
        userBom.setUsername(user.getUsername());
        userBom.setEmail(user.getEmail());
        return userBom;
    }
}
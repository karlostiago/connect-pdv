package com.connectpdv.pdv.service;

import com.util.commons.entity.user.User;

import java.util.List;

public interface UserService {

    User register(User user);
    List<User> listALl();
    User findUserById(Long id);
    User findUserBy(String userName);
}

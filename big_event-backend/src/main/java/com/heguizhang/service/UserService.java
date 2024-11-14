package com.heguizhang.service;

import com.heguizhang.pojo.User;

public interface UserService {
    User findByUsername(String username);
    void register(String username, String password);

}

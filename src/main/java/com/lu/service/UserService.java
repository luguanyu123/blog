package com.lu.service;

import com.lu.po.User;

public interface UserService {
    User login(User user);
    User login2(String username);
}

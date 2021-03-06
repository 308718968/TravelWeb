package com.glc.dao;

import com.glc.bean.User;

public interface UserDao {
    User findUserById(String username);

    boolean createUser(User user);

    boolean activeAccount(String code);
}

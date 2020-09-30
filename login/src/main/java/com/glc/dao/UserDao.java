package com.glc.dao;

import com.glc.bean.User;

public interface UserDao {
    User findUserById(String username);
}

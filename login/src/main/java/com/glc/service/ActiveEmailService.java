package com.glc.service;

import com.glc.dao.UserDao;
import com.glc.util.MySessionUtils;

public class ActiveEmailService {
    public void active(String code) {
        UserDao userDao= MySessionUtils.getMapper(UserDao.class);
        boolean flag = userDao.activeAccount(code);
        if(flag){
            MySessionUtils.commitAndClose();
        }
    }
}

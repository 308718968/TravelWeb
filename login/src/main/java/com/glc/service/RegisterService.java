package com.glc.service;

import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.dao.UserDao;
import com.glc.util.MySessionUtils;
import com.glc.util.UuidUtil;

public class RegisterService {
    public ResultInfo register(User user) {
        user.setCode(UuidUtil.getUuid());
        UserDao userDao = MySessionUtils.getMapper(UserDao.class);
        boolean flag = userDao.createUser(user);
        System.out.println(flag);
        ResultInfo resultInfo  = new ResultInfo();
        if(flag ==true){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("注册成功");
            resultInfo.setData(user);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名已存在");
        }
        MySessionUtils.commitAndClose();
        return resultInfo;
    }
}

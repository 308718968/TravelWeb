package com.glc.service;

import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.dao.UserDao;
import com.glc.util.MySessionUtils;

public class LoginService {
    public ResultInfo findUserByName(User user1) {
        UserDao userDao = MySessionUtils.getSession().getMapper(UserDao.class);
        User res_user = userDao.findUserById(user1.getUsername());
        ResultInfo resultInfo = new ResultInfo();
        if(res_user!=null){
            if("1".equals(res_user.getStatus())){
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号未激活");
            }else if ("2".equals(res_user.getStatus())){
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号冻结中");
            }else{
                if(res_user.getUsername().equals(user1.getUsername())&&res_user.getPassword().equals(user1.getPassword())){
                    resultInfo.setFlag(true);
                }
                else{
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMsg("账号或密码不正确");
                }
            }
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户不存在");
        }
        return resultInfo;
    }

}

package com.glc.service;

import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.dao.UserDao;
import com.glc.util.MySessionUtils;

public class LoginService {
    public ResultInfo login(User user1) {
        //使用mybatis进行数据库查询，返回用户数据
        UserDao userDao = MySessionUtils.getMapper(UserDao.class);
        User res_user = userDao.findUserById(user1.getUsername());
        //结果集
        ResultInfo resultInfo = new ResultInfo();
        //如果有查询到信息，说明用户已注册
        if(res_user!=null){
            //1代表账号未激活
            if("1".equals(res_user.getStatus())){
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号未激活");
                //2代表账号被封
            }else if ("2".equals(res_user.getStatus())){
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号冻结中");
            }else{
                //检验用户输入的账号密码是否正确
                if(res_user.getUsername().equals(user1.getUsername())&&res_user.getPassword().equals(user1.getPassword())){
                    resultInfo.setFlag(true);
                    resultInfo.setData(res_user);
                }
                else{
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMsg("账号或密码不正确");
                }
            }
        }else {
            //用户名没被注册过
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户不存在");
        }
        return resultInfo;
    }

}

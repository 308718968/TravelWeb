package com.glc.service;

import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.dao.UserDao;
import com.glc.util.MailUtils;
import com.glc.util.MySessionUtils;
import com.glc.util.UuidUtil;

public class RegisterService {
    public ResultInfo register(User user) {
        boolean flag=false;
        //设置用户唯一标识符
        user.setCode(UuidUtil.getUuid());
        //调用dao层查询是否有注册过
        UserDao userDao = MySessionUtils.getMapper(UserDao.class);
        User username = userDao.findUserById(user.getUsername());
        //结果集对象
        ResultInfo resultInfo  = new ResultInfo();
        //如果用户名没注册过，就可以注册
        if(username==null){
            flag = userDao.createUser(user);
            if(flag ==true){
                //注册成功后将用户数据放到结果对象中
                resultInfo.setFlag(true);
                resultInfo.setErrorMsg("注册成功");
                resultInfo.setData(user);
                MailUtils.sendMail(user.getEmail(),"<a href='http://192.168.43.78:8080/ActiveEmailServlet?activeCode="+user.getCode()+"'>点击激活途牛旅游账户</a>","旅游项目激活邮件");
            }
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名已存在");
        }
        //提交修改并关闭数据库连接池对象
        MySessionUtils.commitAndClose();
        return resultInfo;
    }
}

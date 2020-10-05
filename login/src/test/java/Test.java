import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.service.ActiveEmailService;
import com.glc.service.CheckCodeService;
import com.glc.service.LoginService;
import com.glc.service.RegisterService;
import com.sun.org.apache.bcel.internal.generic.LDIV;

import java.io.IOException;
import java.util.UUID;

public class Test {
    @org.junit.Test
    public void test(){
        //
        LoginService loginService = new LoginService();
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        ResultInfo resultInfo = loginService.login(user);
        System.out.println(resultInfo);
    }
    @org.junit.Test
    public void TestCheckCode(){
        CheckCodeService checkCodeService = new CheckCodeService();
        String checkcode = checkCodeService.createCheckCode();
        System.out.println(checkcode);
//        BufferedImage checkcodeimage= checkCodeService.checkcodeSreing2Imag(checkcode);
//        ImageIO.write(checkcodeimage,"jpeg",resopnese.getOutputStream());
    }
    @org.junit.Test
    public void TestRegister(){
        RegisterService registerService = new RegisterService();
        User user = new User();
        user.setUid(1);
        user.setUsername("glcc");
        user.setPassword("123456");
        ResultInfo register = registerService.register(user);

    }
    @org.junit.Test
    public void test2(){
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }
    @org.junit.Test
    public void TestEmail(){
        ActiveEmailService activeEmailService = new ActiveEmailService();
        activeEmailService.active("7086b5a1bbad41f9b92e799433be89c1");
    }




}

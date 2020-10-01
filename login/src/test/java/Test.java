import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.service.CheckCodeService;
import com.glc.service.LoginService;
import com.glc.service.RegisterService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
}

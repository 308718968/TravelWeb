import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.service.CheckCodeService;
import com.glc.service.LoginService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Test {
    @org.junit.Test
    public void test(){
        //
        LoginService loginService = new LoginService();
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        ResultInfo resultInfo = loginService.findUserByName(user);
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
    public void TestCode(){

    }
}

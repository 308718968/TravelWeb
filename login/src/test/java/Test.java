import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.service.LoginService;

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
}

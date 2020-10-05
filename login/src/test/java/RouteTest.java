import com.glc.bean.PageBean;
import com.glc.bean.Route;
import com.glc.service.RouteService;
import org.junit.Test;

import java.util.List;

public class RouteTest {
    @Test
    public void test(){
        RouteService routeService = new RouteService();
        PageBean routeList = routeService.search("%北京%",1,10);
        System.out.println(routeList);
    }
}

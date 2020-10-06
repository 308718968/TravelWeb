import com.fasterxml.jackson.core.JsonProcessingException;
import com.glc.bean.Category;
import com.glc.bean.PageBean;
import com.glc.bean.ResultInfo;
import com.glc.bean.Route;
import com.glc.service.CategoryService;
import com.glc.service.RouteService;
import com.glc.service.RouteService2;
import org.junit.Test;

import java.util.List;

public class CategoryTest {
    @Test
    public void test() throws JsonProcessingException {
        CategoryService categoryService = new CategoryService();
        ResultInfo categoryList = categoryService.findAll();

        System.out.println(categoryList);
    }
    @Test
    public void test2() throws JsonProcessingException {
        RouteService2 routeService2 = new RouteService2();
        PageBean pageBean = routeService2.searchById(5,1,10);
        System.out.println(pageBean);
    }
}

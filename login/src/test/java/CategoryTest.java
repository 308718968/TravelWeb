import com.fasterxml.jackson.core.JsonProcessingException;
import com.glc.bean.PageBean;
import com.glc.bean.ResultInfo;
import com.glc.service.CategoryService;
import com.glc.service.RouteService;
import org.junit.Test;


public class CategoryTest {
    @Test
    public void test() throws JsonProcessingException {
        CategoryService categoryService = new CategoryService();
        ResultInfo categoryList = categoryService.findAll();

        System.out.println(categoryList);
    }
    @Test
    public void test2() throws JsonProcessingException {
        RouteService routeService = new RouteService();
        PageBean pageBean = routeService.searchById(5,1,10);
        System.out.println(pageBean);
    }
}

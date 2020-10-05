import com.glc.bean.Category;
import com.glc.bean.ResultInfo;
import com.glc.service.CategoryService;
import org.junit.Test;

import java.util.List;

public class CategoryTest {
    @Test
    public void test(){
        CategoryService categoryService = new CategoryService();
        ResultInfo categoryList = categoryService.findAll();
        System.out.println(categoryList);
    }
}

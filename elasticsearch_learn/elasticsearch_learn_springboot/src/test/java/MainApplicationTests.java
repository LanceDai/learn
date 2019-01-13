import com.example.demo.MainApplication;
import com.example.demo.bean.Pic;
import com.example.demo.dao.PicDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class MainApplicationTests {


    @Autowired
    PicDao picDao;

    @Test
    public void testEs(){
        int page = 0, size = 10;
        Sort sort = new Sort(Sort.Direction.ASC, "publicTime");
        Pageable pageable = PageRequest.of(page,size, sort);
        Page<Pic> picPage = picDao.findAll(pageable);
        List<Pic> pics = picPage.getContent();
        for (Pic pic : pics) {
            System.out.println("pic = " + pic);
        }
    }
}


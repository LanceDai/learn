import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author LanceDai
 * @date 2019/4/21 15:20
 * @description *
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        String resource = "D:\\WorkSpace\\JavaWorkSpace\\learn\\myBatis_learn\\src\\main\\resources\\mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);


    }
}

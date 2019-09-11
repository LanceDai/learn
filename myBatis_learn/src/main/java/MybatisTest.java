import dao.UserTestMapper;
import model.UserTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserTestMapper userTestMapper = sqlSession.getMapper(UserTestMapper.class);
        userTestMapper.deleteAll();
        for (int i = 0; i < 100; i++) {
            userTestMapper.insert(new UserTest(i, i, i));
        }
        System.out.println("userTestMapper.selectAll() = " + userTestMapper.selectAllWithMap());
        sqlSession.commit();
        sqlSession.close();
    }
}

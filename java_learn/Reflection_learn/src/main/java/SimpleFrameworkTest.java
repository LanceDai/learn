import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;


public class SimpleFrameworkTest {

    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        //注意配置文件的位置很重要，通常是获取应用程序所在的文件夹，再“拼出”一个配置文件所放置的路径来
        //以下代码在config.properties文件放在项目根目录下时，工作正常
        //InputStream ips = new FileInputStream("com.learn.config.properties");

        //注意：如果有包名，则配置文件名前要加上包名，但要注意包名的前面不要有“/”
        //比如：cn.edu.bit.cs/com.learn.config.properties
        //InputStream ips=SimpleFrameworkTest.class.getClassLoader().getResourceAsStream("com.learn.config.properties");

        //直接使用class对象的getResourceAsStream方法更简单，它在底层调用ClassLoader的对应方法
        InputStream ips = SimpleFrameworkTest.class.getResourceAsStream("config.properties");

        Properties props = new Properties();
        props.load(ips);
        ips.close();

        String className = props.getProperty("className");
        Collection collections = (Collection) Class.forName(className).newInstance();
        collections.add(new Integer(100));
        collections.add(new Integer(200));
        System.out.println(collections.size());
    }

}

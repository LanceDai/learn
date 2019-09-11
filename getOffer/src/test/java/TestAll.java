import org.junit.Test;
import sun.misc.ProxyGenerator;
import test.CustomizeHandle;
import test.ISubject;
import test.ISubjectImpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class TestAll {
    @Test
    public void test(){
        CustomizeHandle handle = new CustomizeHandle(ISubjectImpl.class) ;
        ISubject subject = (ISubject) Proxy.newProxyInstance(TestAll.class.getClassLoader(), new Class[]{ISubject.class}, handle);
        subject.execute() ;
    }
    @Test
    public void clazzTest(){
//        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
//        try {
//            FileOutputStream out = new FileOutputStream("$Proxy1.class") ;
//            out.write(proxyClassFile);
//            out.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

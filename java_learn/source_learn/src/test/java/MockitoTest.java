import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class MockitoTest {
    static class B {
        A a;
        void setA(A a){
            this.a = a;
        }
        String pp() {
            return a.printf();
        }
    }

    static class A {
        String printf() {
            return this.getClass().getCanonicalName();
        }
        String pppt(){
            return "asdgajhskgd";
        }
    }

    @InjectMocks
    B b;
    @Spy
    A a;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // 使用注解方式需要初始化或者在类上加上注解@RunWith(MockitoJUnitRunner.class)
    }

    @Test
    public void test() {
        Mockito.when(a.printf()).thenReturn("aaa");
        b.setA(a);
        System.out.println("b = " + b.pp());
    }

    @Test
    public void testMock() {
        Target target = MiniMock.mock(Target.class);
        MiniMock.when(target.getA()).thenReturn("1000");
        MiniMock.when(target.getXXX("")).thenReturn("xxx");
        System.out.println(target.getA());
        System.out.println(target.getA());
        System.out.println(target.getA());
        System.out.println(target.getXXX("a"));
    }
}

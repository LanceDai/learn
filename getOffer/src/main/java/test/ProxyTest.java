package test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    interface IuserDao {
        void save();
    }

    static class UserDao implements IuserDao {

        @Override
        public void save() {
            System.out.println("ok");
        }
    }

    /**
     * jdk静态代理
     */
    static class UserDaoProxy implements IuserDao {
        //接收保存目标对象
        private IuserDao target;

        public UserDaoProxy(IuserDao target) {
            this.target = target;
        }

        @Override
        public void save() {
            System.out.println("开始事务...");
            target.save();//执行目标对象的方法
            System.out.println("提交事务...");
        }
    }

    static class ProxyFactory {

        //维护一个目标对象
        private Object target;

        public ProxyFactory(Object target) {
            this.target = target;
        }

        //给目标对象生成代理对象
        public Object getProxyInstance() {
            return Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("开始事务2");
                            //执行目标对象方法
                            Object returnValue = method.invoke(target, args);
                            System.out.println("提交事务2");
                            return returnValue;
                        }
                    }
            );
        }


        /**
         * Cglib子类代理工厂
         * 对UserDao在内存中动态构建一个子类对象
         */
        static class ProxyFactoryWithCglib implements MethodInterceptor {
            //维护目标对象
            private Object target;

            public ProxyFactoryWithCglib(Object target) {
                this.target = target;
            }

            //给目标对象创建一个代理对象
            public Object getProxyInstance() {
                //1.工具类
                Enhancer en = new Enhancer();
                //2.设置父类
                en.setSuperclass(target.getClass());
                //3.设置回调函数
                en.setCallback(this);
                //4.创建子类(代理对象)
                return en.create();

            }

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("开始事务...");
                //执行目标对象的方法
                Object returnValue = method.invoke(target, args);
                System.out.println("提交事务...");
                return returnValue;
            }
        }
    }

    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();
        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();//执行的是代理的方法
    }
}

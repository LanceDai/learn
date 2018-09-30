package cn.edu.bit.cs.method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class InvokeMainMethod {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		//直接调用
		//TestArguments.main(new String[]{"aaa","bbb","ccc"});
		//使用反射方式调用
		Class<?> classObj=Class.forName("cn.edu.bit.cs.method.TestArguments");
		Method main=classObj.getMethod("main", String[].class);
		//如果直接使用以下语句调用main方法，会报告“参数个数错误”，其原因在于JDK1.5支持可变参数但JDK1.4不支持
		//为了保证兼容性，JDK1.5会把接收到的数组“解开”成为单个参数，因此，下面语句会让main方法以为收到了3个参数
		//main.invoke(null,new String[]{"aaa","bbb","ccc"} );
		//解决方法：再给String[]包一层，让它成为一个Object数组的唯一一个元素。
		//main.invoke(null,new Object[]{new String[]{"aaa","bbb","ccc"}});
		//另一种解决方法：将String[]对象强制转换为Object
		main.invoke(null,(Object)new String[]{"aaa","bbb","ccc"});
	}

}

class TestArguments{
	public static void main(String[] args){
		for(String arg : args){
			System.out.println(arg);
		}
	}
}
package cn.edu.bit.cs.factory;

import java.util.*;
import javax.swing.*;

public class ObjectFactory {
	//传给它一个类名，就能得到此类的对象！
	public static Object getInstance(String clsName) {
		try {
			// 创建指定类对应的Class对象
			Class<?> cls = Class.forName(clsName);
			// 返回使用该Class对象所创建的实例
			return cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String className="java.util.GregorianCalendar";
		// 获取实例后需要强制类型转换
		GregorianCalendar d = (GregorianCalendar)
				ObjectFactory.getInstance(className);
		System.out.println(d.getTime());
		// 由于没有类型信息，因此，以下这句也能通过编译，但会引发运行时异常
		JFrame f = (JFrame) ObjectFactory.getInstance(className);
		f.setVisible(true);
	}
}

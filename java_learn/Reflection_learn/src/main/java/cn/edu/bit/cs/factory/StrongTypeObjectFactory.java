package cn.edu.bit.cs.factory;

import java.util.*;
import javax.swing.*;

public class StrongTypeObjectFactory {
	public static <T> T getInstance(Class<T> cls) {
		try {
			return cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		// 获取实例后无需类型转换
		GregorianCalendar d =
				StrongTypeObjectFactory.getInstance(
						GregorianCalendar.class);
		JFrame f = StrongTypeObjectFactory.getInstance(JFrame.class);
		f.setBounds(0, 0, 300, 200);
		f.setTitle(d.getTime().toString());
		f.setVisible(true);
	}
}

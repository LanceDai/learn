package cn.edu.bit.cs.field;

import java.lang.reflect.Field;

public class ChangeStringFieldValue {

	public static void main(String[] args) throws Exception {
		Test obj = new Test();
		System.out.println("原始值：");
		System.out.println("str1=" + obj.str1);
		System.out.println("str2=" + obj.str2);
		System.out.println("str3=" + obj.str3);
		changeStringValue(obj);
		System.out.println("新值：");
		System.out.println("str1=" + obj.str1);
		System.out.println("str2=" + obj.str2);
		System.out.println("str3=" + obj.str3);

	}

	private static void changeStringValue(Object obj) throws Exception {
		Field[] fields = obj.getClass().getFields();
		for (Field field : fields) {
			// 判断Field对象所引用字段的类型
			if (field.getType() == String.class) {
				String oldValue = (String) field.get(obj);
				String newValue = oldValue.replace('b', 'a');
				field.set(obj, newValue);
			}
		}

	}

}

class Test {
	public int value;
	public String str1 = "ball";
	public String str2 = "basketball";
	public String str3 = "aaaaaa";

}

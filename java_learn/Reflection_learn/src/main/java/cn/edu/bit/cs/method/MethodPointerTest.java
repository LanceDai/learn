package cn.edu.bit.cs.method;

import java.lang.reflect.Method;

public class MethodPointerTest {
    public static void main(String[] args) throws Exception {
        // 调用实例对象的实例方法
        System.out.println("调用String对象的实例方法charAt(int index)");
        Method charat = String.class.getMethod("charAt", int.class);
        System.out.println(charat.invoke("hello", 1));

        // get method pointers to the square and sqrt methods
        Method square = MethodPointerTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        // print tables of x- and y-values

        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    /**
     * Returns the square of a number
     *
     * @param x a number
     * @return x squared
     */
    public static double square(double x) {
        return x * x;
    }

    /**
     * Prints a table with x- and y-values for a method
     *
     * @param from the lower bound for the x-values
     * @param to   the upper bound for the x-values
     * @param n    the number of rows in the table
     * @param f    a method with a double parameter and double return value
     */
    public static void printTable(double from, double to, int n, Method f) {
        // print out the method as table header
        System.out.println(f);

        // construct formatter to print with 4 digits precision

        double dx = (to - from) / (n - 1);

        for (double x = from; x <= to; x += dx) {
            try {
                // 第一个参数为对象，第二个参数为方法参数,为null时表示要调用静态方法
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package EXP4;

/**
 * @author LanceDai
 * @date 2019/1/13 11:15
 * @description -XX:+PrintAssembly -Xcomp -XX:CompileCommand=dontinline,*Bar.sum -XX:CompileCommand=compileonly,*Bar.sum
 *   同时加上-XX:+UnlockDiagnosticVMOptions则可以打印编译信息
 */
public class Bar {
    int a = 1;
    static int b = 2;

    public int sum(int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        new Bar().sum(3);
    }
}

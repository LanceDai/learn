package test;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        for (int i = 1; i < 20; i *= 2) {
            System.out.println("i = " + i + " 2 * i = " + 2 * i + " : res = " + tryPresize(i << 1));
        }
    }

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static final int tryPresize(int size) {
        return (size >= (MAXIMUM_CAPACITY >>> 1)) ? MAXIMUM_CAPACITY :
                tableSizeFor(size + (size >>> 1) + 1);
    }

    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}

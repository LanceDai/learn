import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class CommonTest {
    @Test
    public void testPhantomReference() throws InterruptedException {
        System.out.println("**********虚引用测试**********");

        ReferenceQueue<Integer> referenceQueue = new ReferenceQueue<>();

        Integer i = 99999;
        PhantomReference<Integer> pr = new PhantomReference<>(i, referenceQueue);

        i = null;

        System.gc();

        System.out.println(referenceQueue.poll());
        Thread.sleep(100);
        Reference<Integer> reference = (Reference<Integer>) referenceQueue.poll();
        System.out.println(reference.get());
    }
}

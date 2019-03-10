import java.nio.ByteBuffer;

/**
 * @author LanceDai
 * @date 2019/3/10 21:03
 * @description *
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
        writeBuffer.clear();
        String str = "to client : ";
        System.out.println(writeBuffer.position() + " --- " + writeBuffer.limit());
        writeBuffer.put(str.getBytes(), writeBuffer.position(), str.length());
        System.out.println(writeBuffer.position() + " --- " + writeBuffer.limit());


        writeBuffer = ByteBuffer.wrap(str.getBytes());
        System.out.println(writeBuffer.position() + " --- " + writeBuffer.limit());

    }
}

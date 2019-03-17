import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author LanceDai
 * @date 2019/3/17 21:16
 * @description *
 */
public class DoMain {
    static void getPureJavaClass() throws IOException {
        FileReader reader = new FileReader("D:\\WorkSpace\\JavaWorkSpace\\learn\\getOffer\\src\\main\\resourse\\in.txt");
        char[] bytes = new char[1024];
        while (reader.read(bytes) > 0){
            System.out.println(bytes);
        }
    }

    public static void main(String[] args) throws IOException {
        getPureJavaClass();
    }
}

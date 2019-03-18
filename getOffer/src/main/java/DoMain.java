import javax.sound.midi.SoundbankResource;
import java.io.*;

/**
 * @author LanceDai
 * @date 2019/3/17 21:16
 * @description *
 */
public class DoMain {
    static void getPureJavaClass() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\WorkSpace\\JavaWorkSpace\\learn\\getOffer\\src\\main\\resourse\\in.txt"));
        FileWriter writer = new FileWriter("D:\\WorkSpace\\JavaWorkSpace\\learn\\getOffer\\src\\main\\resourse\\out.txt");
        String str;
        int flag = 0;
        while ((str = reader.readLine()) != null) {
            if (str.contains("/*")) {
                flag = 1;
            } else if (str.contains("*/")) {
                flag = 0;
            } else if (flag == 0) {
                writer.write(str + '\n');
            }
        }
        reader.close();
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        getPureJavaClass();
    }
}

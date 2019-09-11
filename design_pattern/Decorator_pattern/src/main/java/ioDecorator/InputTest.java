package ioDecorator;

import java.io.*;

public class InputTest {
    public static void main(String[] args) {
        int c;
        try {
            InputStream in =
                    new LowercaseInputStream(
                            new BufferedInputStream(
                                    new FileInputStream("/home/lance/IdeaProjects/learn/ Design_pattern/Decorator_pattern/src/main/resources/test.txt")));
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author LanceDai
 * @date 2019/3/9 18:28
 * @description *
 */
public class Client {

    public static void main(String[] args) throws IOException {
        //客户端
        //1、创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("localhost", 8000);
        //2、获取输出流，向服务器端发送信息, 将输出流包装成打印流
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.write("这里是客户端" + socket.toString());
        pw.flush();
        socket.shutdownOutput();
        //3、获取输入流，并读取服务器端的响应信息
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String info;
        while ((info = br.readLine()) != null) {
            System.out.println("我是客户端，服务器说：" + info);
        }

        //4、关闭资源
        br.close();
        pw.close();
        socket.close();
    }
}

package bio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author LanceDai
 * @date 2019/3/9 18:28
 * @description *
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //线程池
        ExecutorService executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() + 1,
                200, 0L,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<Runnable>(1000),
                new ThreadFactoryBuilder().setNameFormat("bio-thread").build()
        );

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8000));
        //主线程死循环等待新连接到来
        while (!Thread.currentThread().isInterrupted()) {
            Socket socket = serverSocket.accept();
            //为新的连接创建新的线程
            executor.submit(new ConnectIOHandler(socket));
        }


    }

    static class ConnectIOHandler extends Thread {
        private Socket socket;

        ConnectIOHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
                //死循环处理读写事件
                //读取数据
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String info;
                    while ((info = reader.readLine()) != null) {
                        System.out.println("from client " + info);
                    }
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println(" to client : " + socket.toString());
                    writer.flush();
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

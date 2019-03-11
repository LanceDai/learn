package aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Arrays;

/**
 * @author LanceDai
 * @date 2019/3/9 18:28
 * @description *
 */
public class Server {
    private static int PORT = 8080;
    private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);

    private AsynchronousServerSocketChannel serverChannel;


    private void listen() throws Exception {

        //打开一个服务通道
        //绑定服务端口
        this.serverChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));
        this.serverChannel.accept(this, new AcceptHandler());

        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("运行中...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }


    /**
     * accept到一个请求时的回调
     */
    private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Server> {
        @Override
        public void completed(final AsynchronousSocketChannel client, Server attachment) {
            try {
                System.out.println("远程地址：" + client.getRemoteAddress());

                if (client.isOpen()) {
                    System.out.println("client.isOpen：" + client.getRemoteAddress());
                    readBuffer.clear();
                    client.read(readBuffer, client, new ReadHandler());
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 监听新的请求，递归调用。
                attachment.serverChannel.accept(attachment, this);
            }
        }

        @Override
        public void failed(Throwable exc, Server attachment) {
            try {
                exc.printStackTrace();
            } finally {
                // 监听新的请求，递归调用。
                attachment.serverChannel.accept(attachment, this);
            }
        }
    }


    class ReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            if (result < 0) {
                // 客户端关闭了连接
                Server.close(attachment);
            } else if (result == 0) {
                // 处理空数据
                System.out.println("空数据");
                Server.close(attachment);
            } else {
                // 读取请求，处理客户端发送的数据
                readBuffer.flip();
                int limit = readBuffer.limit();
                int position = readBuffer.position();
                byte[] bytes = new byte[limit - position];
                readBuffer.get(bytes);
                String receiveData = new String(bytes);
                System.out.println("receiveData:" + receiveData);
                System.out.println("bytes :" + Arrays.toString(bytes));
                writeBuffer.clear();
                String str = "to client : " + attachment.toString();
                writeBuffer.put(str.getBytes(), writeBuffer.position(), str.length());
                writeBuffer.flip();
                attachment.write(writeBuffer);
            }
            attachment.read(readBuffer, attachment, this);
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            try {
                System.out.println("exc = " + exc.getMessage());
                attachment.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("正在启动服务...");
            Server server = new Server();
            server.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void close(AsynchronousSocketChannel client) {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

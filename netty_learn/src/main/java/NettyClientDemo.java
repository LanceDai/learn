import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.bootstrap.Bootstrap;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;
import java.util.Scanner;

public class NettyClientDemo implements Runnable {
    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
            b.handler(new LoggingHandler());
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                    pipeline.addLast("handler", new HelloClient());
                }
            });
            ChannelFuture f = b.connect("127.0.0.1", 8888).sync();
            f.channel().writeAndFlush("hello Service!" + Thread.currentThread().getName());
            f.channel().closeFuture().sync();
        } catch (Exception ignored) {
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str;
        while ((str = input.next()) != null) {
            new Thread(new NettyClientDemo(), ">>>this thread the msg is " + str).start();
        }
    }
}

class HelloClient extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client接收到服务器返回的消息:" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exception is general");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketAddress address = ctx.channel().remoteAddress();
        System.out.println("client address = " + address);
    }
}

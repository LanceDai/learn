import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Date;

public class NettyTest {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {

                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline()
                                    .addLast(new LineBasedFrameDecoder(2048))
                                    .addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new ChannelOutboundHandlerAdapter() {
                                        @Override
                                        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                            System.out.println("out -> " + msg);
                                            if (msg instanceof String) {
                                                if ("?\r\n".equalsIgnoreCase((String) msg)) {
                                                    System.out.println("invalid -> " + msg);
                                                } else {
                                                    ctx.write(msg, promise);
                                                }
                                            } else {
                                                ctx.write(msg, promise);
                                            }
                                        }
                                    })
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            assert msg instanceof String;
                                            if (((String) msg).startsWith("?")) {
                                                ctx.write("invalid\r\n");
                                                ctx.flush();
                                            } else {
                                                ctx.fireChannelRead(msg);
                                                super.channelRead(ctx, msg);
                                            }
                                        }
                                    })
                                    .addLast(new SimpleChannelInboundHandler<String>() {
                                        private SocketAddress address;

                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            address = ctx.channel().remoteAddress();
                                            String welcomeStr = "Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n";
                                            System.out.println("welcomeStr = " + welcomeStr);
                                            // Send greeting for a new connection.
                                            ctx.write(welcomeStr);
                                            ctx.write("It is " + new Date() + " now.\r\n");
                                            ctx.flush();
                                        }

                                        @Override
                                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                            cause.printStackTrace();
                                            ctx.close();
                                        }

                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, String request) {
                                            System.out.println("request.toLowerCase() = " + request.toLowerCase());
                                            String response;
                                            boolean close = false;
                                            if (request.isEmpty()) {
                                                response = "Please type something.\r\n";
                                            } else if ("bye".equals(request.toLowerCase())) {
                                                response = "Have a good day!\r\n";
                                                close = true;
                                            } else if ("help".equals(request.toLowerCase())) {
                                                response = "Type bye to close the connecting.\r\n";
                                            } else if ("?".equals(request.toLowerCase())) {
                                                response = "?\r\n";
                                            } else {
                                                response = "Did you say '" + request + "'?\r\n";
                                            }
                                            ChannelFuture future = ctx.write(response);
                                            ctx.flush();
                                            if (close) {
                                                future.addListener(ChannelFutureListener.CLOSE);
                                            }
                                        }

                                        @Override
                                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                            System.out.println(address + " is inactive in " + new Date());
                                        }
                                    });
                        }
                    });
            ChannelFuture future = bootstrap.bind(8888).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully().sync();
        }
    }
}

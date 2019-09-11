import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyClientPoolTest {
    static class NettyServer {
        public void run(final int port) throws Exception {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
                                        .addLast(new StringDecoder()).addLast(new StringEncoder())
                                        .addLast(new DefaultEventExecutorGroup(8),
                                                new NettyServerHandler());
                            }
                        }).option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true);
                ChannelFuture future = b.bind(port).sync();
                future.channel().closeFuture().sync();
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }

        public static void main(String[] args) throws Exception {
            int port = 8080;
            if (args.length > 0) {
                try {
                    port = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            new NettyServer().run(port);
        }
    }

    static class NettyServerHandler extends SimpleChannelInboundHandler<Object> {
        static AtomicInteger count = new AtomicInteger(1);

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channelActived");
            super.channelActive(ctx);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            String body = (String) msg;
            System.out.println(count.getAndIncrement() + ":" + body);
            ctx.writeAndFlush("Welcome to Netty.$_");
        }
    }

    static class NettyChannelPoolHandler implements ChannelPoolHandler {
        @Override
        public void channelReleased(Channel ch) throws Exception {
            System.out.println("channelReleased. Channel ID: " + ch.id());
        }

        @Override
        public void channelAcquired(Channel ch) throws Exception {
            System.out.println("channelAcquired. Channel ID: " + ch.id());
        }

        @Override
        public void channelCreated(Channel ch) throws Exception {
            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
            System.out.println("channelCreated. Channel ID: " + ch.id());
            SocketChannel channel = (SocketChannel) ch;
            channel.config().setKeepAlive(true);
            channel.config().setTcpNoDelay(true);
            channel.pipeline()
                    .addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
                    .addLast(new StringDecoder())
                    .addLast(new StringEncoder())
                    .addLast(new NettyClientHander());

        }
    }

    static class NettyClientHander extends ChannelInboundHandlerAdapter {
        static AtomicInteger count = new AtomicInteger(1);

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(count.getAndIncrement() + ":" + msg);
        }
    }

    static class NettyPoolClient {
        final EventLoopGroup group = new NioEventLoopGroup();
        final Bootstrap strap = new Bootstrap();
        InetSocketAddress addr1 = new InetSocketAddress("127.0.0.1", 8080);
        InetSocketAddress addr2 = new InetSocketAddress("10.0.0.11", 8888);

        ChannelPoolMap<InetSocketAddress, SimpleChannelPool> poolMap;

        void build() throws Exception {
            strap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true);

            poolMap = new AbstractChannelPoolMap<InetSocketAddress, SimpleChannelPool>() {
                @Override
                protected SimpleChannelPool newPool(InetSocketAddress key) {
                    return new FixedChannelPool(strap.remoteAddress(key), new NettyChannelPoolHandler(), 2);
                }
            };
        }

        public static void main(String[] args) throws Exception {
            NettyPoolClient client = new NettyPoolClient();
            client.build();
            final String ECHO_REQ = "Hello Netty.$_";
            for (int i = 0; i < 10; i++) {
                // depending on when you use addr1 or addr2 you will get different pools.
                final SimpleChannelPool pool = client.poolMap.get(client.addr1);
                Future<Channel> f = pool.acquire();
                f.addListener((FutureListener<Channel>) f1 -> {
                    if (f1.isSuccess()) {
                        Channel ch = f1.getNow();
                        ch.writeAndFlush(ECHO_REQ);
                        // Release back to pool
                        pool.release(ch);
                    }
                });
            }
        }
    }
}

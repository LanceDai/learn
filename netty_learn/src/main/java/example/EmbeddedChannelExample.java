package example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author LanceDai
 * @date 2019/6/8 20:38
 * @description *
 */
public class EmbeddedChannelExample {
    public static void main(String[] args) {

    }

    static class FixedLengthFrameDecoder extends ByteToMessageDecoder {
        private final int frameLength;

        FixedLengthFrameDecoder(int frameLength) {
            if (frameLength <= 0) {
                throw new IllegalArgumentException(
                        "frameLength must be a positive integer: " + frameLength
                );
            }
            this.frameLength = frameLength;
        }

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            while (in.readableBytes() >= frameLength) {
                ByteBuf byteBuf = in.readBytes(frameLength);
                out.add(byteBuf);
            }
        }
    }
}




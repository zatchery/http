package network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * This is to handle the truncating of the messages. We are dealing with TCP streams at this point.
 * 
 * @author Z
 *
 */
final class ZachsByteMessageDecoder extends ByteToMessageDecoder
{
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
      throws Exception
  {
    if (in.readableBytes() < 1)
    {
      // For handling that pesky empty streams
      ctx.close();
      return;
    }
    byte[] lastBytes = new byte[3];
    in.getBytes(in.readableBytes() - 4, lastBytes, 0, 3);
    String lastByte = new String(lastBytes);
    // If there is no body then the message will end with a CRLF
    // If there is a message body it will end with a new line
    // TODO: Better handling of optional message body
    if (lastByte.equals("\r\n\r") || lastBytes[2] == (byte) 10)
    {
      out.add(in.readBytes(in.readableBytes()));
    }
    else
    {
      return;
    }
  }
}

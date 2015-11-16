package network;

import http.handlers.HttpRequestHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

@RequiredArgsConstructor
public class SimpleRequestHandler extends SimpleChannelInboundHandler<String>
{

  private static final Logger logger = LoggerFactory.getLogger("SimpleRequestHandler");

  private final HttpRequestHandler httpHandler;

  @Override
  public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception
  {
    logger.debug("Received message: [{}]", msg);
    /*
     * Get the Headers
     */
    List<String> headers = Lists.newArrayList(msg.split("\\n"));

    ctx.write("Hello");
    channelReadComplete(ctx);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx)
  {
    ctx.flush();
    ctx.close();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
  {
    logger.error(cause.getMessage());
    ctx.close();
  }

}

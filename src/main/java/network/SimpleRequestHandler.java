package network;

import http.handlers.HttpRequestHandler;
import http.messages.request.HttpRequest;
import http.messages.request.RequestHeader;
import http.messages.response.HttpResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.stream.Collectors;

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
     * Get the Headers and Message Body, separated by two CRLF
     */

    String[] messageBody = msg.split("\r\n\r\n");
    String body = messageBody[messageBody.length - 1];

    List<String> rawHeaders = Lists.newArrayList(messageBody[0].split("\\n"));
    List<RequestHeader> headers =
        rawHeaders.stream().map((header) -> new RequestHeader(header)).collect(Collectors.toList());
    HttpRequest req = new HttpRequest(headers, body);
    HttpResponse resp = httpHandler.handle(req);

    ctx.write(resp.toString());
    if (resp.getBody() != null)
    {
      ctx.write(resp.getBody());
    }
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
    logger.error("Exception caught in handler", cause);
    ctx.close();
  }

}

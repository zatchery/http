package network;

import http.handlers.GetHandler;
import http.handlers.HttpRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.HostAndPort;

public class HttpServer
{
  private static final Logger logger = LoggerFactory.getLogger("HttpConnection");

  private final ServerBootstrap boot;
  private final HostAndPort address;
  private ChannelFuture channelFuture;
  
  //HTTP handlers
  private final HttpRequestHandler handler;
  // GET
  private final GetHandler getHandler;

  public HttpServer(HostAndPort address)
  {
    getHandler = new GetHandler();
    this.handler = new HttpRequestHandler(getHandler);

    this.address = address;
    this.boot =
        new ServerBootstrap()
            .group(new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()))
            .channel(NioServerSocketChannel.class)
            // .handler(new LoggingHandler(LogLevel.ERROR))
            .childHandler(new ChannelInitializer<SocketChannel>()
            {

              @Override
              protected void initChannel(SocketChannel ch) throws Exception
              {
                ch.pipeline().addLast(new ZachsByteMessageDecoder());
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new SimpleRequestHandler(handler));
              }
            });
  }

  public void connect()
  {
    logger.info("Connecting to [{}]: ", address);
    boot.bind(address.getHostText(), address.getPort());
  }

}

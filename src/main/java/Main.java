import network.HttpServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.HostAndPort;

/**
 * author Zach Morin
 */
public class Main
{
  private static final Logger logger = LoggerFactory.getLogger("Main");

  /**
   * Graceful shutdown not implemented yet. When it is time to shutdown, it stops quite abruptly
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    try
    {
    logger.info("Service starting");
    boolean run = true;
    HttpServer conn =
        new HttpServer(HostAndPort.fromParts("localhost", 8080));
    conn.connect();
      // and We're running
    while(run){}
    }
    finally
    {
    logger.info("Service stopped:");
    }
  }

}

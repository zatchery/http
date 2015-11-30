package http.handlers;

import http.messages.Header;
import http.messages.request.HttpRequest;
import http.messages.request.RequestHeader;
import http.messages.request.RequestLine;
import http.messages.response.HttpResponse;
import http.messages.response.ResponseHeader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * Handler of the GET requests
 * 
 * @author Z
 *
 */
public class GetHandler
{
  private static final Logger logger = LoggerFactory.getLogger("GetHandler");

  public HttpResponse handle(HttpRequest req)
  {
    List<RequestHeader> headers = req.getHeaders();
    RequestLine requestLine = new RequestLine(headers.get(0).getRawHeader());
    try
    {
      Path path = Paths.get(requestLine.getUri());
      byte[] readAllBytes = Files.readAllBytes(path);

      List<Header> respHeaders = Lists.newArrayList();
      RequestHeader acceptHeader =
          headers.parallelStream()
              .filter((rawHeader) -> rawHeader.getRawHeader().contains("Accept"))
          .map((h) ->
          {
                h.parse();
            return h;
              }).findFirst().get();
      
      InputStream encodedBody;
      // TODO: figure out what format the request wants;
      // Add Content-Type, Content-Length
      // Accept: */*
      // Accept-Encoding: gzip, deflate, sdch
      // Accept-Language: en-US,en;q=0.8,pt;q=0.6
      // If it is picky about what kind of reply it wants
      respHeaders
          .add(new ResponseHeader("Content-Length", Integer.toString(readAllBytes.length)));
      respHeaders.add(new ResponseHeader("Content-Disposition", "attachment;filename="
          + path.getFileName()));
      if (!acceptHeader.getPayload().contains("*/*"))
      {
        // TODO: Handle the client negotion a little better;
        logger
            .warn("We don't support any other type of encoding just yet, defualting to application/octet-stream");
        respHeaders.add(new ResponseHeader("Content-Type", "application/octet-stream"));
      }
      else
      {
        // Else we just encode it normally
        respHeaders.add(new ResponseHeader("Content-Type", "text/plain"));
        encodedBody = new ByteArrayInputStream(readAllBytes);
      }

      return new HttpResponse(200, "OK", respHeaders, readAllBytes);

    }
    catch (NoSuchFileException e)
    {
      logger.info("File not found for path: [{}]", requestLine.getUri());
      return new HttpResponse(404, "File Not found", Lists.newArrayList(), null);
    }
    catch (IOException e)
    {
      logger.error("Uh oh, something has gone wrong: [{}]", e);
    }
    // I don't know how you got here, but you broke it. I hope you're happy.
    return new HttpResponse(500, "Internal Server Error", Lists.newArrayList(), null);
  }

}

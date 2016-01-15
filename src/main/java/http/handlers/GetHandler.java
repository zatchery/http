package http.handlers;

import http.messages.Header;
import http.messages.request.HttpRequest;
import http.messages.request.RequestHeader;
import http.messages.request.RequestLine;
import http.messages.response.HttpResponse;
import http.messages.response.ResponseHeader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * Handler of the GET requests
 * 
 * @author Z
 *
 */
@RequiredArgsConstructor
public class GetHandler
{
  private static final String CONTENT_LENGTH = "Content-Length";
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String CONTENT_ENCODING = "Content-Encoding";
  private static final Logger logger = LoggerFactory.getLogger("GetHandler");

  public HttpResponse handle(HttpRequest req)
  {
    List<RequestHeader> headers = req.getHeaders();
    RequestLine requestLine = new RequestLine(headers.get(0).getRawHeader());
    try
    {

      List<Header> respHeaders = Lists.newArrayList();
      Optional<RequestHeader> acceptEncodingHeader =
          headers.parallelStream()
              .filter((rawHeader) -> rawHeader.getRawHeader().contains("Accept-Encoding:"))
              .map((h) ->
              {
                h.parse();
                return h;
              }).findFirst();

      InputStream encodedBody;
      Path path = Paths.get(requestLine.getUri());
      //This could be fun if path exceeds the max size of an int.
      int size = (int) Files.size(path);

      OutputStream outputStream = new ByteArrayOutputStream(size);
      Files.copy(path, outputStream);

      respHeaders.add(new ResponseHeader("Content-Disposition", "attachment; filename="
          + path.getFileName()));

      String fileName = path.getFileName().toString();
      if (fileName.lastIndexOf(".pdf") == fileName.length() - 4)
      {
        respHeaders.add(new ResponseHeader(CONTENT_TYPE, "application/pdf"));
      }
      else
      {
        respHeaders
            .add(new ResponseHeader(CONTENT_TYPE, "application/octet-stream"));
      }

      respHeaders.add(new ResponseHeader("Connection", "close"));

      return new HttpResponse(200, "OK", respHeaders, outputStream);

    }
    catch (NoSuchFileException e)
    {
      logger.info("File not found for path: [{}]", requestLine.getUri());
      List<Header> fourOhFourHeaders = Lists.newArrayList();
      fourOhFourHeaders.add(new ResponseHeader("Connection", "close"));
      return new HttpResponse(404, "File Not found", fourOhFourHeaders, null);
    }
    catch (IOException e)
    {
      logger.error("Uh oh, something has gone wrong: [{}]", e);
    }
    // I don't know how you got here, but you broke it. I hope you're happy.
    return new HttpResponse(500, "Internal Server Error", Lists.newArrayList(), null);
  }

}

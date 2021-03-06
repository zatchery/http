package http.messages.request;

import http.messages.Header;

import java.util.List;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Request       = Request-Line              ; Section 5.1
                         *(( general-header        ; Section 4.5
                         | request-header         ; Section 5.3
                         | entity-header ) CRLF)  ; Section 7.1
                        CRLF
                        [ message-body ]          ; Section 4.3
 */
@Getter
public class HttpRequest
{
  public HttpRequest(List<RequestHeader> headers, Object messageBody)
  {
    this.method = Method.parse(headers.get(0).getRawHeader());
    this.headers = headers;
    this.messageBody = messageBody;
  }

  private static final Logger logger = LoggerFactory.getLogger("HttpRequest");
  private final Method method;
  private final List<RequestHeader> headers;
  private Object messageBody;
  
  
  
  
  /**
   * Method         = "OPTIONS"                ; Section 9.2
                      | "GET"                    ; Section 9.3
                      | "HEAD"                   ; Section 9.4
                      | "POST"                   ; Section 9.5
                      | "PUT"                    ; Section 9.6
                      | "DELETE"                 ; Section 9.7
                      | "TRACE"                  ; Section 9.8
                      | "CONNECT"                ; Section 9.9
   */
  public enum Method
  {
    OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT;

    /**
     * Parses the Method off of a Request line, returns null if request is malformed or non rfc
     * compliant
     * 
     * @param rawHeader
     * @return {@link Method}
     */
    public static Method parse(String rawHeader)
    {
      String method = rawHeader.split(" ")[0];
      switch (method)
      {
        case "OPTIONS":
          return Method.OPTIONS;
        case "GET":
          return Method.GET;
        case "HEAD":
          return Method.HEAD;
        case "POST":
          return Method.POST;
        case "PUT":
          return Method.PUT;
        case "DELETE":
          return Method.DELETE;
        case "TRACE":
          return Method.TRACE;
        case "CONNECT":
          return Method.CONNECT;
        default:
          logger.error("What kind of request was that? [{}]", rawHeader);
          return null;
      }
    }
  }

  /**
   * Parses string into a request header
   * 
   * @param rawHeader
   * @return {@link RequestHeader}
   */
  public static RequestHeader parse(Header rawHeader)
  {
    return null;
  }

}

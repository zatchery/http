package http.messages.request;

import http.messages.request.HttpRequest.Method;
import lombok.Value;

/**
 * The Request-Line begins with a method token, followed by the Request-URI and the protocol
 * version, and ending with CRLF. The elements are separated by SP characters. No CR or LF is
 * allowed except in the final CRLF sequence.
 * 
 * Request-Line = Method SP Request-URI SP HTTP-Version CRLF
 * 
 * @author Z
 *
 */
@Value
public class RequestLine extends RequestHeader
{
  private final Method method;
  private final String uri;
  private final String httpVersion;

  public RequestLine(String rawHeader)
  {
    super(rawHeader);
    String[] split = rawHeader.split(" ");
    this.method = Method.valueOf(split[0]);
    this.uri = split[1];
    this.httpVersion = split[2];
  }
}

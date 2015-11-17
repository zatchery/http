package http.handlers;

import http.messages.request.HttpRequest;
import http.messages.response.HttpResponse;

public class HttpRequestHandler
{

  public HttpResponse handle(HttpRequest req)
  {
    return new HttpResponse(200, "OK", null);
  }

}

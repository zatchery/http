package http.handlers;

import http.messages.request.HttpRequest;
import http.messages.response.HttpResponse;

import com.google.common.collect.Lists;

public class HttpRequestHandler
{

  public HttpResponse handle(HttpRequest req)
  {
    if(req.getMethod() == null)
    {
      return new HttpResponse(400, "Bad Request", Lists.newArrayList(), null);
    }
    
    return new HttpResponse(200, "OK", null, null);
  }

}

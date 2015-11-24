package http.handlers;

import http.messages.Header;
import http.messages.request.HttpRequest;
import http.messages.response.HttpResponse;
import http.messages.response.ResponseHeader;

import java.util.List;

import lombok.RequiredArgsConstructor;

import com.google.common.collect.Lists;

@RequiredArgsConstructor
public class HttpRequestHandler
{

  private final GetHandler getHandler;

  public HttpResponse handle(HttpRequest req)
  {
    List<Header> headers = Lists.newArrayList();
    if(req.getMethod() == null)
    {
      return new HttpResponse(400, "Bad Request", Lists.newArrayList(), null);
    }
    switch (req.getMethod())
    {
      case GET:
        return getHandler.handle(req);
      default:
        // We only support GET requests right now
        ResponseHeader allow = new ResponseHeader("Allow", "GET");
        headers.add(allow);
        return new HttpResponse(405, "Method Not Allowed", headers, null);
    }
    
  }

}

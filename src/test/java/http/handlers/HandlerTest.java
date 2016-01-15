package http.handlers;
import http.messages.request.HttpRequest;
import http.messages.request.RequestHeader;
import http.messages.response.HttpResponse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.Lists;

@RunWith(JUnit4.class)
public class HandlerTest
{

  private HttpRequestHandler handler;
  private GetHandler getHandler;

  @Before
  public void setUp() throws Exception
  {
    handler = new HttpRequestHandler(getHandler);
  }

  @Test
  public void test()
  {
    RequestHeader get = new RequestHeader("GET /file.txt HTTP/1.1");
    RequestHeader host = new RequestHeader("Host: localhost:8080");
    RequestHeader useragent = new RequestHeader("User-Agent: curl/7.43.0");
    RequestHeader accept = new RequestHeader("Accept: */*");
    List<RequestHeader> headers = Lists.newArrayList();
    Object messageBody = null;
    HttpRequest req = new HttpRequest(headers, messageBody);
    HttpResponse handle = handler.handle(req);
    
  }

}

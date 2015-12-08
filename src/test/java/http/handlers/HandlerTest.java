package http.handlers;
import static org.junit.Assert.*;
import http.messages.request.HttpRequest;
import http.messages.request.RequestHeader;

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
    List<RequestHeader> headers = Lists.newArrayList();
    Object messageBody = "";
    HttpRequest req = new HttpRequest(headers, messageBody);
    handler.handle(req);
    fail("Not yet implemented");
  }

}

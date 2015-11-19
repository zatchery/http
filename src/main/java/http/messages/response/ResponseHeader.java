package http.messages.response;

import http.messages.Header;

public class ResponseHeader extends Header
{
//@formatter:off
  /**
   *  response-header = Accept-Ranges           ; Section 14.5
                       | Age                     ; Section 14.6
                       | ETag                    ; Section 14.19
                       | Location                ; Section 14.30
                       | Proxy-Authenticate      ; Section 14.33
                       | Retry-After             ; Section 14.37
                       | Server                  ; Section 14.38
                       | Vary                    ; Section 14.44
                       | WWW-Authenticate        ; Section 14.47
   */
  //@formatter:on

  private String headerType;
  private String value;

  private void parse()
  {
    // find out what kind of request this is.
  }

}

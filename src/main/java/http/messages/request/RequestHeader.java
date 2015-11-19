package http.messages.request;

import http.messages.Header;
import lombok.Getter;
import lombok.Setter;

// @formatter:off
/**
 * request-header = Accept                   ; Section 14.1
                      | Accept-Charset           ; Section 14.2
                      | Accept-Encoding          ; Section 14.3
                      | Accept-Language          ; Section 14.4
                      | Authorization            ; Section 14.8
                      | Expect                   ; Section 14.20
                      | From                     ; Section 14.22
                      | Host                     ; Section 14.23
                      | If-Match                 ; Section 14.24
                      | If-Modified-Since        ; Section 14.25
                      | If-None-Match            ; Section 14.26
                      | If-Range                 ; Section 14.27
                      | If-Unmodified-Since      ; Section 14.28
                      | Max-Forwards             ; Section 14.31
                      | Proxy-Authorization      ; Section 14.34
                      | Range                    ; Section 14.35
                      | Referer                  ; Section 14.36
                      | TE                       ; Section 14.39
                      | User-Agent               ; Section 14.43
 */
//@formatter:on
@Getter
@Setter
public class RequestHeader extends Header
{
  private String headerType;
  private String payload;

  private void parse()
  {
    // find out what kind of request this is.
  }

  public RequestHeader(String rawHeader)
  {
    super.rawHeader = rawHeader;
  }
}

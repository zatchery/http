package http.messages.request;

import http.messages.Header;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestHeader extends Header
{
  private ReqHeaderType headerType;
  private String payload;

  public void parse()
  {
    String[] split = rawHeader.split(":");
    this.headerType = ReqHeaderType.parse(split[0].trim());
    this.payload = split[1].trim();

  }

  public RequestHeader(String rawHeader)
  {
    super.rawHeader = rawHeader;
  }

//@formatter:off
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

  public enum ReqHeaderType
  {
    Accept,
    AcceptCharset,
    AcceptEncoding,
    AcceptLanguage,
    Authorization,
    Expect,
    From,
    Host,
    IfMatch,
    IfModifiedSince,
    IfNoneMatch,
    IfRange,
    IfUnmodifiedSince,
    MaxForwards,
    ProxyAuthorization,
    Range,
    Referer,
    TE,
    UserAgent,
    CustomHeader;

    public static ReqHeaderType parse(String string)
    {
      switch (string)
      {
        case "Accept":
          return Accept;
        case "Accept-Charset":
          return AcceptCharset;
        case "Accept-Encoding":
          return AcceptEncoding;
        case "Accept-Language":
          return AcceptLanguage;
        case "Authorization":
          return Authorization;
        case "Expect":
          return Expect;
        case "From":
          return From;
        case "Host":
          return Host;
        case "If-Match":
          return IfMatch;
        case "If-Modified-Since":
          return IfModifiedSince;
        case "If-None-Match":
          return IfNoneMatch;
        case "If-Range":
          return IfRange;
        case "If-Unmodified-Since":
          return IfUnmodifiedSince;
        case "Max-Forwards":
          return MaxForwards;
        case "Proxy-Authorization":
          return ProxyAuthorization;
        case "Range":
          return Range;
        case "Referer":
          return Referer;
        case "TE":
          return TE;
        case "User-Agent":
          return UserAgent;
        default:
          return CustomHeader;
      }
    }

  }

}

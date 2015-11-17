package http.messages.request;

import lombok.Value;

@Value
public class RequestMethodNotFoundException extends Exception
{
  private static final long serialVersionUID = -6083563279739812116L;
  private final String rawHeader;
}

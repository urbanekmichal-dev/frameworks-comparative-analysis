package gatling.test.common;

import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

public class Constants {
    public static final double SUCCESS_RATE = 95;
    public static final String ACCEPTED_ENCODINGS = "gzip, deflate, br";
    public static final String USER_AGENT = "PostmanRuntime/7.32.2";

    private static HttpProtocolBuilder HTTP_PROTOCOL =http.baseUrl(Properties.getBaseUrl())
           .disableCaching()
           .acceptHeader(Headers.Values.APPLICATION_JSON)
           .acceptEncodingHeader(Constants.ACCEPTED_ENCODINGS)
           .userAgentHeader(Constants.USER_AGENT);

   public static HttpProtocolBuilder getHttpProtocolBuilder(){
       return HTTP_PROTOCOL;
   }
}

package gatling.test.common;

import java.time.Duration;
import static gatling.test.common.SystemPropertiesUtil.getAsIntOrElse;
import static gatling.test.common.SystemPropertiesUtil.getAsStringOrElse;


public class Properties {
    private static final int USERS = getAsIntOrElse("users",10);
    private static final int DURATION = getAsIntOrElse("duration",1);
    public static final Duration SSE_WORK_TIME = Duration.ofSeconds(10);
    public static final Duration SSE_PAUSE_TIME = Duration.ofSeconds(1);
    public static final String BASE_URL = getAsStringOrElse("baseUrl", "http://localhost:8080");

    public static final int P95_RESPONSE_TIME_MS = getAsIntOrElse("p95ResponseTimeMs", 1000);

    public static int getUsers() {
        return USERS;
    }

    public static int getDuration() {
        return DURATION;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}



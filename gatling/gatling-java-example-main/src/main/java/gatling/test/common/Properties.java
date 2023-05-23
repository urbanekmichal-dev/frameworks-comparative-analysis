package gatling.test.common;

import boopickle.Base;

import java.time.Duration;

import static gatling.test.example.simulation.SystemPropertiesUtil.getAsStringOrElse;

public class Properties {
    private static final String NOTIFICATION_USERS = "10";
    private static final String NOTIFICATION_TIME = "1";
    private static final String SORTING_USERS = "10";
    private static final String SORTING_TIME = "1";
    public static final Duration SSE_WORK_TIME = Duration.ofSeconds(10);
    public static final Duration SSE_PAUSE_TIME = Duration.ofSeconds(1);

    public static final String BASE_URL = getAsStringOrElse("baseUrl", "http://localhost:8080");

    public static int getNotificationUsers() {
        return Integer.parseInt(NOTIFICATION_USERS);
    }

    public static int getNotificationTime() {
        return Integer.parseInt(NOTIFICATION_TIME);
    }
    public static int getSortingUsers(){
        return Integer.parseInt(SORTING_USERS);
    }
    public static int getSortingTime(){
        return Integer.parseInt(SORTING_TIME);
    }
    public static String getBaseUrl() {
        return BASE_URL;
    }
}



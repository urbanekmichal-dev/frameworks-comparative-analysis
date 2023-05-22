package gatling.test.common;

public class Paths {

    //NOTIFICATION
    public static final String SINGLE_NOTIFICATION = "api/notifications/datetime";
    public static final String DELAYED_NOTIFICATION = "api/notifications/datetime/3000";
    public static final String SSE_NOTIFICATION = "api/notifications/datetimeSSE?delay=5000&count=6";

    //CURD
    public static final String TASKS = "api/tasks";

    //SORT
    public static final String QUICK_SORT = "/api/sort/quick-sort";
    public static final String MERGE_SORT = "/api/sort/merge-sort";
    public static final String INSERTION_SORT = "/api/sort/insertion-sort";
    public static final String BUBBLE_SORT = "/api/sort/bubble-sort";

    //CRYPTO
    public static final String ENCRYPT = "/api/crypto/encryptText";
    public static final String DECRYTP = "api/crypto/decryptFile/encrypted.txt";


}

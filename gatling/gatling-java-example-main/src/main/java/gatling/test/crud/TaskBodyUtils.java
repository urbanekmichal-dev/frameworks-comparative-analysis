package gatling.test.crud;

public class TaskBodyUtils {
    public static final String TASK_NAME = "name";
    public static final String TASK_DESCRIPTION = "description";


    private static final String POST_BODY_TEMPLATE = """
            {
            "name": "%s",
            "description": "%s"
            }
            """;

    public static String createTaskBody(final String name, final String description){
        return String.format(POST_BODY_TEMPLATE,name,description);
    }

    public static String generateRandomString(){
        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

        final java.util.Random rand = new java.util.Random();

        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
        }
        return builder.toString();
    }

}

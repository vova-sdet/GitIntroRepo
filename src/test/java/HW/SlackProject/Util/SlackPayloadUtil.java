package HW.SlackProject.Util;

public class SlackPayloadUtil {

    public static String getSlackMessage (String message) {

        return "{\n" +
                "  \"channel\": \"C0164SXRETU\",\n" +
                "  \"text\": \"" + message + "\"\n" +
                "}";

    }
}

package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayloadUtil {

    public static String getPetPayload (int id, String name, String status) {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
    }

    public static String getUserPayload (String name, String jobTitle) {
        return "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"" + jobTitle + "\"\n" +
                "}";
    }

    public static String getSlackMessage (String message) {
        return "{\n" +
                "  \"channel\": \"C0164SXRETU\",\n" +
                "  \"text\": \"" + message + "\"\n" +
                "}";
    }

    public static String generateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }
}

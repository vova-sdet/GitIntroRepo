package API.com.Practice.pojoDeserialize;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakingBad {

    private static final String ALIVE = "alive";
    private static final String DECEASED = "deceased";
    private static final String PRESUMED_DEAD = "presumed dead";
    private static final String UNKNOWN = "unknown";

    @Test
    public void getCharacters() throws URISyntaxException, IOException {

        // https://breakingbadapi.com/api/characters
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("breakingbadapi.com");
        uri.setPath("api/characters");

        HttpGet get = new HttpGet(uri.build());
        get.setHeader("Accept", "Application/json");

        HttpResponse response = client.execute(get);

        // System.out.println("Status code for GET request is: " + response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<BBpojo> deserializedObject = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<List<BBpojo>>() {
                });

        System.out.println(deserializedObject.get(0).getName());

        Map<String, List<String>> charactersByStatus = new HashMap<>();
        List<String> alive = new ArrayList<>();
        List<String> deceased = new ArrayList<>();
        List<String> presumedDead = new ArrayList<>();
        List<String> unknownStatus = new ArrayList<>();

        for (int i = 0; i < deserializedObject.size(); i++) {

            if (deserializedObject.get(i).getStatus().equalsIgnoreCase(ALIVE)) {
                alive.add(deserializedObject.get(i).getName());
            } else if (deserializedObject.get(i).getStatus().equalsIgnoreCase(DECEASED)) {
                deceased.add(deserializedObject.get(i).getName());
            } else if (deserializedObject.get(i).getStatus().equalsIgnoreCase(PRESUMED_DEAD)) {
                presumedDead.add(deserializedObject.get(i).getName());
            } else {
                unknownStatus.add(deserializedObject.get(i).getStatus());
                // System.out.println("Unknown status: " + deserializedObject.get(i).getStatus());
            }

        }

        charactersByStatus.put(ALIVE, alive);
        charactersByStatus.put(DECEASED, deceased);
        charactersByStatus.put(PRESUMED_DEAD, presumedDead);
        charactersByStatus.put(UNKNOWN, unknownStatus);

        System.out.println("List of alive characters: " + charactersByStatus.get(ALIVE));
        System.out.println("List of deceased characters: " + charactersByStatus.get(DECEASED));
        System.out.println("List of presumedDead characters: " + charactersByStatus.get(PRESUMED_DEAD));
        System.out.println("List of character with unknown status: " + charactersByStatus.get(UNKNOWN));

    }

    @Test
    public void getCharacterById() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("breakingbadapi.com");
        uri.setPath("api/characters/12");

        HttpGet get = new HttpGet(uri.build());
        get.setHeader("Accept", "Application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<BBCharPojo> bbCharPojos = objectMapper.readValue(response.getEntity().getContent(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, BBCharPojo.class));

        System.out.println(String.format("%s is %s, portrayed as: %s, in %s category.", bbCharPojos.get(0).getName(),
                bbCharPojos.get(0).getStatus(), bbCharPojos.get(0).getPortrayed(), bbCharPojos.get(0).getCategory()));

        System.out.println(bbCharPojos.get(0).getName() + " is " + bbCharPojos.get(0).getStatus() + ", portrayed as: " +
                bbCharPojos.get(0).getPortrayed() + ", in " + bbCharPojos.get(0).getCategory() + " category.");
    }
}

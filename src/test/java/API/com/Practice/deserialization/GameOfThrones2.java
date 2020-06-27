package API.com.Practice.deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameOfThrones2 {

    @Test
    public void gameOfThrones() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("api.got.show").setPath("api/map/characters").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        ArrayList<Map<String, Object>> characters = (ArrayList<Map<String, Object>>) deserializedResponse.get("data");
        Map<String, Integer> houses = new HashMap<>();


        for (int i = 0; i < characters.size(); i++) {
            Map<String, Object> character = (Map<String, Object>) characters.get(i);
            String houseName = (String) character.get("house");

            if (!houses.keySet().contains(houseName)) {
                houses.put(houseName, 0);
            }

            if (character.get("dateOfDeath") == null) {
                int count = houses.get(houseName);
                houses.replace(houseName, ++count);
            }
        }

        System.out.println(houses);

        System.out.println(characters.size());
    }
}

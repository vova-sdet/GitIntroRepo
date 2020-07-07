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
import java.util.*;

public class StarWars {

    @Test
    public void getAllPlanets() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        // https://swapi.dev/api/planets
        URI uri = uriBuilder.setScheme("https").setHost("swapi.dev").setPath("api/planets").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        // Map<String, String> planetPopulation = new HashMap<>();
        Map<String, Long> planetPopulation = new HashMap<>();

        List<Map<String, Object>> results = (ArrayList<Map<String, Object>>) deserializedResponse.get("results");

        for (Map<String, Object> result : results) {
            try {
                planetPopulation.put((String) result.get("name"), Long.parseLong(result.get("population").toString()));
            } catch (NumberFormatException e) {
                System.out.println("Planet's population is unknown " + e);
            }
        }

        Set<String> keys = planetPopulation.keySet();

        for (String key : keys) {
            System.out.println(key + " -> " + planetPopulation.get(key));
        }

        System.out.println(planetPopulation);
    }
}

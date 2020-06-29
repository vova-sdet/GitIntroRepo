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

public class GameOfThrones {

    @Test
    public void gameOfThrones() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("api.got.show").setPath("com.api/map/characters").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        ArrayList<Map<String, Object>> characters = (ArrayList<Map<String, Object>>) deserializedResponse.get("data");

        Map<String, Integer> doma = new HashMap<>();
        Map<String, Map<Integer, Integer>> totalPeople= new HashMap<>();

        for (int i = 0; i < characters.size(); i++) {
            Map<String, Object> character = (Map<String, Object>) characters.get(i);
            String houseName = (String) character.get("house");
            doma.put(houseName, 0);
        }

        System.out.println(doma.size());
        System.out.println(doma);

        for (int i = 0; i < characters.size(); i++) {
            Map<String, Object> character = (Map<String, Object>) characters.get(i);

            if (character.get("dateOfDeath") != null) {
                String houseName = (String) character.get("house");

                if (doma.containsKey(houseName)) {
                    int count = doma.get(houseName);
                    doma.replace(houseName, ++count);
                }
            } else {
                continue;
            }

        }

//        Map<String, Integer> doma = new HashMap<>();
//
//        for (int i = 0; i < characters.size(); i++) {
//            Map<String, Object> character = (Map<String, Object>) characters.get(i);
//            String houseName = (String) character.get("house");
//
//            if(!doma.keySet().contains(houseName)) {
//                doma.put(houseName, 0);
//            }
//
//            if(character.get("dateOfDeath") == null) {
//                int count = doma.get((houseName));
//                doma.replace(houseName,++count);
//            }
//        }

        System.out.println(doma);

        System.out.println(characters.size());

    }

}

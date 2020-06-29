package API.com.Practice.deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task {
    @Test
    public void countCatFacts() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        // http://cat-fact.herokuapp.com/facts
        URI uri = uriBuilder.setScheme("http").setHost("cat-fact.herokuapp.com").setPath("facts").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");
        // get.setHeader("Content-Type", "application/json");

        HttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail("Status code is not as expected!");
        }

        // Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, ArrayList> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, ArrayList>>() {});

        // System.out.println(deserializedResponse);

        ArrayList<Object> facts = (ArrayList<Object>) deserializedResponse.get("all");

        System.out.println(facts.size());
        int count = 0;

//        for(Object fact : facts) {
//            Map <String, Object> singleFact = (Map<String, Object>) fact;
//            String text = (String)singleFact.get("text");
//            System.out.println("Fact -> " + text);
//            if (!text.contains("cat") && !text.contains("Cat") && !text.contains("kitten")
//                    && !text.contains("Kitten") && !text.contains("tiger") && !text.contains("Tiger") &&
//                !text.contains("lion") && !text.contains("Lion")) {
//                count++;
//            }
//        }

        for(Object fact : facts) {
            Map <String, Object> singleFact = (Map<String, Object>) fact;
            Map<String, Object> user =
                    (Map<String, Object>) singleFact.get("user");
            if (singleFact.get("user") != null) {
                Map<String, String> name = (Map<String, String>) user.get("name");
                System.out.println("First -> " + name.get("first"));
                System.out.println("Last -> " + name.get("last"));
            }
        }

        System.out.println(count);
    }

    @Test
    public void task2() throws IOException, URISyntaxException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        //https://reqres.in/api/users/
        URI uri = uriBuilder.setScheme("http").setHost("reqres.in").setPath("com.api/users").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail("Status code is not as expected!");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        // System.out.println(deserializedResponse);
        Set<String> keys = deserializedResponse.keySet();

        int num = 0;

        for(String key : keys) {
            if (num < 4) {
                System.out.println(deserializedResponse.get(key));
                num++;
            } else {
                break;
            }
        }

        List<Map <String, Object>> users = (List<Map <String, Object>>) deserializedResponse.get("data");

        for (Map<String, Object> user : users) {
            System.out.println("User's first name is : " + user.get("first_name"));
        }
    }

}

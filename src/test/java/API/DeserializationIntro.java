package API;

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
import java.util.Map;

public class DeserializationIntro {

    @Test
    public void deserialization1() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        URI uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io/v2").setPath("pet/1").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");
        // get.setHeader("Content-Type", "application/json");

        HttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail("Status code is not as expected!");
        }

        // Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        System.out.println("The id from response body is: " + deserializedResponse.get("id"));
        System.out.println("The category from response body is: " + deserializedResponse.get("category"));

        Map<String, Object> category = (Map<String, Object>) deserializedResponse.get("category");
        System.out.println("Category name is: " + category.get("name"));

        ArrayList tags = (ArrayList) deserializedResponse.get("tags");
        Map<String, Object> firstTag = (Map<String, Object>)tags.get(0);
        System.out.println("Tags name is: " + firstTag.get("name"));

    }

    @Test
    public void deserialization2() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        URI uri = uriBuilder.setScheme("https").setHost("reqres.in").setPath("com.api/users/2").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");
        // get.setHeader("Content-Type", "application/json");

        HttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail("Status code is not as expected!");
        }

        // Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        Map<String, Object> data = (Map<String, Object>) deserializedResponse.get("data");
        System.out.println(data.get("email"));
    }
}

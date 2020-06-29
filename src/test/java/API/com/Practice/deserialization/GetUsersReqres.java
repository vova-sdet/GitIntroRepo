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
import java.util.List;
import java.util.Map;

public class GetUsersReqres {

    //https://reqres.in/api/users/?per_page=12

    @Test
    public void getUsers() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("reqres.in").setPath("com.api/users").setCustomQuery("per_page=12").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> users = (List<Map<String, Object>>)deserializedResponse.get("data");

        int count = 0;

        for (Map<String, Object> user : users) {
            System.out.println(user.get("email"));
            count++;
        }

        System.out.println(deserializedResponse.get("per_page"));

        Assert.assertEquals(deserializedResponse.get("per_page"), count);
    }
}

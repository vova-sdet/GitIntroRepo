package API.pojo.getUser2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class UserRequest2 {

    @Test
    public void getUser() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("reqres.in");
        uri.setPath("api/users/7");

        HttpGet get = new HttpGet(uri.build());
        get.setHeader("Accept", "Application/json");

        HttpResponse response = client.execute(get);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        System.out.println("Status code for GET request is: " + response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        UserPojo2 deserializedObject = objectMapper.readValue(response.getEntity().getContent(), UserPojo2.class);

        System.out.println(deserializedObject.getAd().get("company"));
//        System.out.println(deserializedObject.getAd().get(deserializedObject.getCompany()));

    }
}

package API.com.Practice.petstore;

import Utils.PayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class GetVerification extends PayloadUtil {

    int expectedId = 5055;
    String expectedName = "larki";
    String expectedStatus = "good boy";

    @Test
    public void postRequestNewPet() throws URISyntaxException, IOException {

        /*
        1. Create new pet
        2. Verify id, name, status
         */

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet").build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");

        System.out.println("Building request body");
        HttpEntity entity = new StringEntity(getPetPayload(expectedId, expectedName, expectedStatus));

        post.setEntity(entity);

        System.out.println("Start POST method execution");
        HttpResponse response = client.execute(post);
        System.out.println("Finished POST method execution");

        // HttpStatus.SC_OK -> 200
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        System.out.println("Mapping response body with JAVA object");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        int actualId = (int)parsedResponse.get("id");
        String actualName = (String)parsedResponse.get("name");
        String actualStatus = (String)parsedResponse.get("status");

        Assert.assertEquals(expectedId, actualId);
        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void verifyPet() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet").build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");

        int expectedId = 5055;
        String expectedName = "larki";
        String expectedStatus = "good boy";

        System.out.println("Building request body");
        HttpEntity entity = new StringEntity(getPetPayload(expectedId, expectedName, expectedStatus));

        post.setEntity(entity);

        System.out.println("Start POST method execution");
        HttpResponse response = client.execute(post);
        System.out.println("Finished POST method execution");

        // HttpStatus.SC_OK -> 200
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        System.out.println("Mapping response body with JAVA object");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        int actualId = (int)parsedResponse.get("id");
        String actualName = (String)parsedResponse.get("name");
        String actualStatus = (String)parsedResponse.get("status");

        Assert.assertEquals(expectedId, actualId);
        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedStatus, actualStatus);

        // Execute get request and do verifications
        System.out.println("\n" + "Execute get request and do verifications" + "\n");

        uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet/" + expectedId).build();
        System.out.println("Started executing GET request");
        HttpGet get = new HttpGet(uri);
        System.out.println("Finished executing GET request");

        HttpResponse getResponse = client.execute(get);

        Assert.assertEquals(HttpStatus.SC_OK, getResponse.getStatusLine().getStatusCode());
        System.out.println("Deserializing response");
        parsedResponse = objectMapper.readValue(getResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        Assert.assertEquals(expectedId, parsedResponse.get("id"));
        Assert.assertEquals(expectedName, parsedResponse.get("name"));
        Assert.assertEquals(expectedStatus, parsedResponse.get("status"));
    }

}

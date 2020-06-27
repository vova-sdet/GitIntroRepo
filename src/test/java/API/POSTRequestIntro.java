package API;

import Utils.PayloadUtil;
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

public class POSTRequestIntro extends PayloadUtil {

/*
1. Open a client (POSTMAN, terminal)
2. Specify the HTTP method (GET, POST)
3. Specify URL/URI/endpoint
4. Add query parameter (if needed)
5. Add header parameters (content-type, accept)
6. Add body parameter (for POST)
7. Execute (click on Send button)
 */

    @Test
    public void postRequest() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("reqres.in");
        uri.setPath("api/users");

        HttpPost post = new HttpPost(uri.build());
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");

        // specify body
        HttpEntity entity = new StringEntity("{\n" +
                "    \"name\": \"tima\",\n" +
                "    \"job\": \"boss\"\n" +
                "}");

        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        // HttpStatus.SC_CREATED -> 201
        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

    }

    @Test
    public void postRequestNewPet() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet").build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");

        HttpEntity entity = new StringEntity(getPetPayload(7711, "larki", "good boy"));

        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        // HttpStatus.SC_OK -> 200
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    // create 25 pets (in cucumber -> scenario outline)

    @Test
    public void getNewPet() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io/v2").setPath("pet/3").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        System.out.println("Response status code: " + response.getStatusLine().getStatusCode());
        System.out.println("Application status type: " + response.getEntity().getContentType().getValue());

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
    }

}

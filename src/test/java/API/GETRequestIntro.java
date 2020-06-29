package API;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class GETRequestIntro {

    @Test
    public void getRequest() throws URISyntaxException, IOException {

//        Steps to execute a API call/request:
//        1. Open/ launch client (open POSTMAN)
//        2. Specify the method type (GET/POST/PUT/DELETE)
//        3. Specify the URL/URI( Uniform Resource locator)/(Uniform Resource identifier) URL == URI
//        4. Execute (click on send button)
//        5. Check the response status code
//        6. Check the response body
//        7. Execute (click on Send button)


        // http://cat-fact.herokuapp.com/facts

        HttpClient client = HttpClientBuilder.create().build();

        // Specify the URL/URI (endpoint)
        // https://petstore.swagger.io//v2/pet/1488
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("petstore.swagger.io");
        uri.setPath("v2/pet/22022");

        // Specify the HTTP method (GET)
        HttpGet get = new HttpGet(uri.build());
        // Add header params
        get.setHeader("Accept", "Application/json");

        // Execute
        HttpResponse response = client.execute(get);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        System.out.println("Status code for GET request is: " + response.getStatusLine().getStatusCode());

        Assert.assertEquals("application/json", response.getEntity().getContentType().getValue());

    }

    @Test
    public void getRequest2() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/findByStatus");
        uriBuilder.setCustomQuery("status=sold");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        Assert.assertEquals("application/json", response.getEntity().getContentType().getValue());

    }


}

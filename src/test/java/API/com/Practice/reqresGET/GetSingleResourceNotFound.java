package API.com.Practice.reqresGET;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GetSingleResourceNotFound {

    @Test
    public void getSingleResourceNotFound() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("reqres.in").setPath("com.api/unknown/23").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application.json");

        HttpResponse response = client.execute(get);

        System.out.println(response.getStatusLine().getStatusCode());
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusLine().getStatusCode());
    }
}

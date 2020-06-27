package API.com.Practice.petstore;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class Create25Dogs {

    @Test
    public void postDog() throws URISyntaxException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("pet").build();



    }
}

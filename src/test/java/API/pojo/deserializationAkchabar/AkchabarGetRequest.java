package API.pojo.deserializationAkchabar;

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

public class AkchabarGetRequest {

    @Test
    public void deserializeResponse() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("http").setHost("rates.akchabar.kg").setPath("get.json").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();
        AkchabarPojo akchabarPojo = objectMapper.readValue(response.getEntity().getContent(), AkchabarPojo.class);
        System.out.println(akchabarPojo.getRates().get("btc"));

    }
}

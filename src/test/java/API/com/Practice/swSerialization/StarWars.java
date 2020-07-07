package API.com.Practice.swSerialization;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
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

public class StarWars {

    @Test
    public void getStarShips() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("swapi.dev");
        uriBuilder.setPath("api/starships");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        Assert.assertEquals("application/json", response.getEntity().getContentType().getValue());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        SWPojo swPojo = objectMapper.readValue(response.getEntity().getContent(), SWPojo.class);
        System.out.println(swPojo.getResults().get(0).getMGLT());

    }
}

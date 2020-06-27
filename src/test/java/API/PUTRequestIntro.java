package API;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class PUTRequestIntro {

    @Test
    public void updateUser() throws URISyntaxException, IOException {


        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users/2").build();

        HttpPut put = new HttpPut(uri);
        put.setHeader("Accept", "application/json");
        put.setHeader("Content-Type", "application/json");

        HttpEntity entity = new StringEntity("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"SDET\"\n" +
                "}");

        put.setEntity(entity);

        HttpResponse response = client.execute(put);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

    }

}

package API.com.Practice.deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.List;
import java.util.Map;


public class Tronalddump {

    @Test
    public void getValue() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("tronalddump.io").setPath("/random/quote").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        Map<String, Object> links = (Map<String, Object>) deserializedResponse.get("_links");
        Map<String, String> href = (Map<String, String>) links.get("self");
        System.out.println("1 -> " + href.get("href"));

        Map<String, Map<String, Object>> urls = (Map<String, Map<String, Object>>) deserializedResponse.get("_links");
        System.out.println("2 -> " + urls.get("self").get("href"));

        System.out.println(deserializedResponse.get("value"));


        Map<String, List<Map<String, Object>>> embedded =
                (Map<String, List<Map<String, Object>>>) deserializedResponse.get("_embedded");

        Map<String, Map<String, String>> finalHref = (Map<String, Map<String, String>>) embedded.get("author").get(0).get("_links");
        System.out.println("FINAL HREF ---> " + finalHref.get("self").get("href"));

    }

}

package API.com.Practice.serialization;

import Utils.PayloadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SerializationIntro {

    @Test
    public void serialized1() throws IOException {

        Pet pet = new Pet("Charlie", 5, "cool guy");
        pet.setId(8412);
        pet.setPhotoUrl("https://s17.petpics.amazom.com");

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File("target/jsons/pet2.json"), pet);
    }

    @Test
    public void serialized2() throws IOException {

        Car car = new Car("Aventador", 2020, 5.0, "green", 4);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File("target/jsons/car2.json"), car);
    }

    @Test
    public void createPet() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet").build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");

        String petPayload = PayloadUtil.generateStringFromResource("target/jsons/pet2.json");
        HttpEntity entity = new StringEntity(petPayload);

        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

}

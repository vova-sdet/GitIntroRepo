package StepDefinitions.API;

import Utils.PayloadUtil;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.net.URISyntaxException;

public class CreateDogSteps extends PayloadUtil {


    @Given("the user creates {int} new dogs and validate API response status message is {string}")
    public void the_user_creates_new_dogs_and_validate_API_response_status_message_is(int numOfDogs, String expectedResponse) throws URISyntaxException, IOException, InterruptedException {

        int startingId = 1995;
        Faker faker = new Faker();

        for(int i = startingId; i <= numOfDogs+startingId; i++) {
            HttpClient client = HttpClientBuilder.create().build();
            URIBuilder uri = new URIBuilder();
            uri.setScheme("https").setHost("petstore.swagger.io/v2").setPath("pet");

            HttpPost post = new HttpPost(uri.build());
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");

            HttpEntity entity = new StringEntity(getPetPayload(i, faker.dog().name(), faker.dog().breed()));
            post.setEntity(entity);

            System.out.println("Dog name: " + faker.dog().name());
            System.out.println("Dog breed is: " + faker.dog().breed());
            HttpResponse response = client.execute(post);

            Assert.assertEquals(expectedResponse, response.getStatusLine().getReasonPhrase());

            System.out.println(i+ " created");

        }
    }
}

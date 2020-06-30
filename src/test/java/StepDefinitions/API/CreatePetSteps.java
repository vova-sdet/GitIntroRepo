package StepDefinitions.API;

import Utils.PayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class CreatePetSteps extends PayloadUtil {

    HttpResponse response;
    int expectedId = 5055;
    String expectedName = "larki";
    String expectedStatus = "good boy";

    // executing a request
    @When("user crates a pet with id, name, status")
    public void user_crates_a_pet_with_id_name_status() throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet").build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");

        int expectedId = 5055;
        String expectedName = "larki";
        String expectedStatus = "good boy";

        System.out.println("Building request body");
        HttpEntity entity = new StringEntity(getPetPayload(expectedId, expectedName, expectedStatus));

        post.setEntity(entity);

        System.out.println("Start POST method execution");
        response = client.execute(post);
        System.out.println("Finished POST method execution");
    }

    // validation of response status code
    @Then("the status code is OK")
    public void the_status_code_is_OK() {
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    // response body verification
    @Then("pet with id, name , status is created")
    public void pet_with_id_name_status_is_created() throws IOException {
        System.out.println("Mapping response body with JAVA object");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        int actualId = (int)parsedResponse.get("id");
        String actualName = (String)parsedResponse.get("name");
        String actualStatus = (String)parsedResponse.get("status");

        Assert.assertEquals(expectedId, actualId);
        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedStatus, actualStatus);
    }

    // CREATING PETS USING SCENARIO OUTLINE AND EXAMPLES TABLE

    @When("user crates a pet with {string}, {string}, {string}")
    public void user_crates_a_pet_with(String expectedId, String expectedName, String expectedStatus) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet").build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");

        System.out.println("Building request body");
        HttpEntity entity = new StringEntity(getPetPayload(Integer.parseInt(expectedId), expectedName, expectedStatus));

        post.setEntity(entity);

        System.out.println("Start POST method execution");
        response = client.execute(post);
        System.out.println("Finished POST method execution");
    }

    @Then("pet with {string}, {string}, {string} is created")
    public void pet_with_is_created(String expectedId, String expectedName, String expectedStatus) throws IOException {
        System.out.println("Mapping response body with JAVA object");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        int actualId = (int)parsedResponse.get("id");
        String actualName = (String)parsedResponse.get("name");
        String actualStatus = (String)parsedResponse.get("status");

        Assert.assertEquals(Integer.parseInt(expectedId), actualId);
        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedStatus, actualStatus);
    }
}

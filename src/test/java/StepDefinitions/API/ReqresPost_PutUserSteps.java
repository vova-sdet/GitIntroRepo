package StepDefinitions.API;

import Utils.PayloadUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ReqresPost_PutUserSteps extends PayloadUtil {

    HttpResponse response;

    String originalName = "Teymur";
    String originalJobDescription = "Student";

    String updatedJobDescription = "SDET";

    @When("{string} user at reqres.in")
    public void user_at_reqres_com(String action) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        URI postUri = uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users").build();
        URI putUri = uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users/2").build();
        HttpPost post = new HttpPost(postUri);
        HttpPut put = new HttpPut(putUri);
        HttpEntity entity;

        switch (action) {
            case "create":
                post.setHeader("Accept", "application/json");
                post.setHeader("Content-Type", "application/json");

                entity = new StringEntity(getUserPayload(originalName, originalJobDescription));
                post.setEntity(entity);

                response = client.execute(post);
                System.out.println("Response status text for create action is: " + response.getStatusLine().getReasonPhrase());
                break;

            case "update":
                put.setHeader("Accept", "application/json");
                put.setHeader("Content-Type", "application/json");

                entity = new StringEntity(getUserPayload(originalName, updatedJobDescription));
                put.setEntity(entity);

                response = client.execute(put);
                System.out.println("Response status text for create action is: " + response.getStatusLine().getReasonPhrase());
                break;

            default:
                System.out.println("Wrong API call!");
        }
    }

    @Then("Verify status code {int}")
    public void verify_status_code(int expectedStatus) {

        System.out.println(response.getStatusLine().getStatusCode());
        Assert.assertEquals(expectedStatus, response.getStatusLine().getStatusCode());
    }

    @Then("Verify {string} information")
    public void verify_information(String string) {

    }
}

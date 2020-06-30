package HW.SlackProject.StepDefinitions;

import HW.SlackProject.Util.SlackConfigReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Scenario5 {

    @When("the user deletes message from slack via POST request")
    public void the_user_deletes_message_from_slack_via_POST_request() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("slack.com").setPath("api/chat.delete").setCustomQuery("channel=C0164SXRETU&ts=" + Scenario1.ts).build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", SlackConfigReader.getProperties("token"));

        client.execute(post);
    }

    @Then("the user verifies the message is gone via GET request")
    public void the_user_verifies_the_message_is_gone_via_GET_request() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("slack.com").setPath("api/conversations.history").setCustomQuery("channel=C0164SXRETU").build();

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Authorization", SlackConfigReader.getProperties("token"));

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> messages = (List<Map<String, Object>>) deserializedResponse.get("messages");

        List<String> messagesText = new ArrayList<>();

        for (Map<String, Object> message : messages) {

            Assert.assertTrue(!message.get("ts").equals(Scenario1.ts));
        }
    }
}

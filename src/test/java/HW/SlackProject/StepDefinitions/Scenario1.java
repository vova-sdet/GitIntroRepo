package HW.SlackProject.StepDefinitions;

import HW.SlackProject.Util.SlackConfigReader;
import HW.SlackProject.Util.SlackPayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Scenario1 extends SlackPayloadUtil {

    public static String ts = null;

    @Given("the user sends message to slack via POST request")
    public void the_user_sends_message_to_slack_via_POST_request() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("https").setHost("slack.com").setPath("api/chat.postMessage").build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", SlackConfigReader.getProperties("token"));

        HttpEntity entity = new StringEntity(getSlackMessage(SlackConfigReader.getProperties("message")));
        post.setEntity(entity);

        client.execute(post);
    }

    @Then("the user verifies the message via GET request")
    public void the_user_verifies_the_message_via_GET_request() throws URISyntaxException, IOException {
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
            String text = (String)message.get("text");
            messagesText.add(text);

            if(text.equals(SlackConfigReader.getProperties("message"))) {
                ts = (String) message.get("ts");
                System.out.println(ts);
            }
        }

        Assert.assertTrue(messagesText.contains(SlackConfigReader.getProperties("message")));
    }
}

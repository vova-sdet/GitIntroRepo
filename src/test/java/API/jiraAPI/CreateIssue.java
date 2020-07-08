package API.jiraAPI;

import Utils.PayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class CreateIssue {

    public String getAuthCookieValue() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/auth/1/session").build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");

        HttpEntity entity = new StringEntity(PayloadUtil.getJiraAuthorizationPayload());
        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        JiraLoginPojo deserializedResponse = objectMapper.readValue(response.getEntity().getContent(), JiraLoginPojo.class);

        return String.format("%s=%s", deserializedResponse.getSession().get("name"), deserializedResponse.getSession().get("value"));
    }

    @Test
    public void createIssue() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        URI uri = uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/issue").build();

        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        post.setHeader("Cookie", getAuthCookieValue());
        System.out.println(getAuthCookieValue());

        String summary = "Creating issue through Java code";
        String description = "First API call through Java code";
        String issueType = "Bug";

        HttpEntity entity = new StringEntity(PayloadUtil.getJiraCreateBugPayload(summary, description, issueType));
        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        // JiraCreateIssuePojo deserializedResponse = objectMapper.readValue(response.getEntity().getContent(), JiraCreateIssuePojo.class);
        Map<String, String> parsedResponse = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, String>>() {
        });

        for (String key : parsedResponse.keySet()) {
            System.out.printf("%s -> %s\n", key, parsedResponse.get(key));
        }
    }
}

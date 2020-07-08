package API.jiraAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class GetMaxResultsLimitTest extends AuthCookie{

    @Test
    public void verifyResult() throws IOException, URISyntaxException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        // http://localhost:8080/rest/api/2/search?jql=assignee=test_user&maxResults=2
        URI uri = uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/search").
                setCustomQuery("search?jql=assignee=test_user&maxResults=2").build();

        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("Cookie", getAuthCredentials());

        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });

        List<Map<String, Object>> issueList = new ArrayList<>();
        issueList = (List) parsedResponse.get("issues");

        Assert.assertEquals(2, issueList.size());

        Set<String> issueKeys = new HashSet<>();

        for(Map<String, Object> issue : issueList) {
            issueKeys.add((String) issue.get("key"));
        }

        Assert.assertEquals(2, issueKeys.size());
    }

    @Test
    public void advancedDeserialization() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        // http://localhost:8080/rest/api/2/search?jql=assignee=test_user&maxResults=2
        URI uri = uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/search").
                setCustomQuery("search?jql=assignee=test_user&maxResults=2").build();

        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("Cookie", getAuthCredentials());

        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode  parsedResponse = objectMapper.readTree(response.getEntity().getContent());
        JsonNode issues = parsedResponse.get("issues");
        System.out.println(parsedResponse.get("maxResults"));

        System.out.println(issues.get(0).get("self").asText());
    }
}

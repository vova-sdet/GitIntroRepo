package API.com.Practice;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class iTunesGetRequest1 {

    @Test
    public void getTravissScott() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        // http://itunes.apple.com/search?term=travissscott
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("itunes.apple.com");
        uri.setPath("search");
        uri.setCustomQuery("term=travissscott");

        HttpGet httpGet = new HttpGet(uri.build());
        httpGet.setHeader("Accept", "application/javascript");

        HttpResponse response = client.execute(httpGet);

        System.out.println("response code " + response.getStatusLine().getStatusCode());
        System.out.println("response text " + response.getStatusLine().getReasonPhrase());
        System.out.println("Accept header type ->" + response.getEntity().getContentType().getValue());

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        Assert.assertEquals("OK", response.getStatusLine().getReasonPhrase());
        Assert.assertEquals("text/javascript; charset=utf-8", response.getEntity().getContentType().getValue());

    }
}

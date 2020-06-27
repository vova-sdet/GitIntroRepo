package API.com.Practice.iTunesGET;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class iTunesGetRequest6 {

    @Test
    public void getWeeknd() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("itunes.apple.com");
        uri.setPath("search");
        uri.setCustomQuery("term=weeknd");

        HttpGet get = new HttpGet(uri.build());
        get.setHeader("Accept", "application/javascript");

        HttpResponse response = client.execute(get);

        System.out.println("response status code: " + response.getStatusLine().getStatusCode());
        System.out.println("response status text: " + response.getStatusLine().getReasonPhrase());
        System.out.println("Accept header type: " + response.getEntity().getContentType().getValue());

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        Assert.assertEquals("OK", response.getStatusLine().getReasonPhrase());
        Assert.assertEquals("text/javascript; charset=utf-8", response.getEntity().getContentType().getValue());

    }
}

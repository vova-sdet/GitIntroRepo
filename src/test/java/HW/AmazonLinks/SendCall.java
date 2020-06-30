package HW.AmazonLinks;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SendCall {

    public static URI constructURI(String link) throws URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder();

        if (!link.endsWith("com/")) {
            String scheme = link.substring(0, link.indexOf("://"));
            // System.out.println("Scheme: " + scheme);
            uriBuilder.setScheme(scheme);

            link = link.substring(link.indexOf('.') + 1);
            // System.out.println("Link 2: " + link);
            String host = link.substring(0, link.indexOf('/'));
            // System.out.println("Host" + host);
            uriBuilder.setHost(host);

            link = link.substring(link.indexOf('/') + 1);
            // System.out.println("Link 3: " + link);
            String path = link;
            // System.out.println(path);
            uriBuilder.setPath(path);
        } else {
            String scheme = link.substring(0, link.indexOf("://"));
            // System.out.println("Scheme: " + scheme);
            uriBuilder.setScheme(scheme);

            link = link.substring(link.indexOf('.') + 1);
            // System.out.println("Link 2: " + link);
            String host = link.substring(0, link.indexOf('/'));
            // System.out.println("Host" + host);
            uriBuilder.setHost(host);
        }

        URI uri = uriBuilder.build();

        return uri;
    }

    public static int getStatusCode(String link) throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URI uri = constructURI(link);

        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        return response.getStatusLine().getStatusCode();
    }
}

package HW.AmazonLinks;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class TestGetCall {

    @Test
    public void printWorkingLinks() throws IOException, URISyntaxException {

        Set<String> workingLinks = new HashSet<>();

        for(String link : GetAllLinks.getLinks()) {

            try {
                if (SendCall.getStatusCode(link) == 200) {
                    workingLinks.add(link);
                    System.out.println(SendCall.getStatusCode(link));
                    System.out.println(link);
                };

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}

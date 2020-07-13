package API.restAssured;

import API.pojo.FootballPojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AdvFootballCompetitionsGET {

    ResponseSpecification responseSpecification;
    FootballPojo footballPojo;
    Response response;

    @Before
    public void steUp() {

        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "v2/competitions";
        requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON).setContentType(ContentType.JSON).build();
        responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();

        response = given().spec(requestSpecification).get()
                .then().assertThat().spec(responseSpecification).extract().response();

    }

    @Test
    public void getCompetitions() {

        // System.out.println(footballPojo.getCompetitions().get(0).get("name"));

        footballPojo = response.as(FootballPojo.class);
        for(Map<String, Object> competition: footballPojo.getCompetitions()) {
            if((Integer)competition.get("id") >= 2100) {
                System.out.println(competition.get("name"));
                System.out.println(competition.get("id"));
            }
        }
    }

    @Test
    public void advanced() {

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> competitions = jsonPath.getList("competitions");
        System.out.println(competitions.size());

        int count = 0;
        for(Map<String, Object> competition: competitions) {
            if((Integer)competition.get("id") >= 2100) {
                System.out.println(competition.get("name"));
                System.out.println(competition.get("id"));
                count++;
            }
        }

        System.out.println(count);
    }

    @Test
    public void advanced2() {

        List<String> sortedCompetitions = response.path("competitions.findAll {it.id >= 2100}.name");
        System.out.println(sortedCompetitions.size());
    }

    @Test
    public void advanced3() {

        List<String> sortedCompetitions = response.path("competitions.findAll {it.area.name == 'Mexico'}.name");

        System.out.println(sortedCompetitions);
    }


}

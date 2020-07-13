package API.restAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class AdvancedFootballScorers {

    Response response;

    @Before
    public void setUp() {


        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "v2/competitions/2000/scorers";

        RestAssured.requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON)
                .addHeader("X-Auth-Token", "6b3ba4cbb80649b793e0d3d5dc5f8160").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = given().spec(requestSpecification).get().then().spec(responseSpecification).extract().response();
    }

    @Test
    public void test() {


        int numberOfGoals = response.path("scorers.find {it.player.name == 'Harry Kane' }.numberOfGoals");
        Assert.assertEquals(6, numberOfGoals);
    }

    @Test
    public void test2() {

        String country = response.path("scorers.find {it.player.name == 'Denis Cheryshev' }.team.name");
        Assert.assertEquals("Russia", country);
    }

    @Test
    public void test3() {

        String playerWithMaxGoals = response.path("scorers.max {it.numberOfGoals}.player.name");
        System.out.println(playerWithMaxGoals);
    }

    @Test
    public void test4() {

        String playerWithMinGoals = response.path("scorers.min {it.numberOfGoals}.player.name");
        System.out.println(playerWithMinGoals);
    }

    @Test
    public void test5() {

        int min = response.path("scorers.min {it.numberOfGoals}.numberOfGoals");
        List<String> minScorers = response.path("scorers.findAll {it.numberOfGoals == " + min + "}.player.name");
        System.out.println(minScorers);

        List<String> minScorers1=response.path(String.format("scorers.findAll{ it.numberOfGoals == %s}.player.name", min));
        System.out.println(minScorers1);
    }

    @Test
    public void test6() {

        response.then().body("scorers.find {it.player.name == 'Harry Kane' }.numberOfGoals", Matchers.equalTo(6));
    }

    @Test
    public void test7() {

        response.then().body("scorers.find{it.player.name == 'Harry Kane'}.numberOfGoals", Matchers.equalTo(6));
        response.then().assertThat().body("scorers.findAll {it.team.name =='Russia'}.size",Matchers.equalTo(0));
    }

    @Test
    public void test8() {

        // scorers.findAll {it.team.name == 'Russia'} -> it will return scorers objects with Russia team name
        response.then().assertThat().body("scorers[0].team.name", Matchers.equalTo("England")).log().body();
    }

    @Test
    public void test9() {

        response.then().body("scorers.collect {it.team.name}", Matchers.hasItem("England"));
        response.then().body("scorers.collect {it.team.name}", Matchers.hasItems("England", "Portugal"));
    }

}

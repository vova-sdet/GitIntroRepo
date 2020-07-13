package API.restAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class RestAssuredIntro2 {

    @Before
    public void setUp() {

        RestAssured.requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @Test
    public void getReq1() {

        given().spec(requestSpecification).when().get("https://breakingbadapi.com/api/characters/50")
                .then().spec(responseSpecification)
                .body("[0].name", Matchers.equalToIgnoringCase("Juan Bolsa"))
                .and().body("[0].occupation", Matchers.hasSize(1));
    }

    @Test
    public void getReq2() {

        given().spec(requestSpecification).when().get("https://breakingbadapi.com/api/characters/50")
                .then().spec(responseSpecification)
                .and().rootPath("[0]").body("appearance.size()", Matchers.greaterThanOrEqualTo(2))
        .and().body("nickname", Matchers.equalToIgnoringCase("Don Juan"))
        .and().body("status", Matchers.equalToIgnoringCase("Deceased"));
    }

    @Test
    public void getReq3() {

        RestAssured.baseURI = "https://api.got.show";
        RestAssured.basePath = "api/map/characters/byId/5cc0743504e71a0010b852d9";
        given().spec(requestSpecification).when().get()
                .then().spec(responseSpecification)
                .rootPath("data").body("books", Matchers.hasItem("A Storm of Swords"))
                .body("dateOfBirth", Matchers.equalTo(283))
        .body("house", Matchers.is("House Stark")).body("male", Matchers.is(true))
        .body("male", Matchers.isA(Boolean.class));
    }

}

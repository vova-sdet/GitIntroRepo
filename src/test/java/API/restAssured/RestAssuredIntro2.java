package API.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredIntro2 {

    @Test
    public void getReq1() {

        given().header("Accept", ContentType.JSON).when().get("https://breakingbadapi.com/api/characters/50")
                .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
                .body("[0].name", Matchers.equalToIgnoringCase("Juan Bolsa"))
                .and().body("[0].occupation", Matchers.hasSize(1));
    }

    @Test
    public void getReq2() {

        given().header("Accept", ContentType.JSON).when().get("https://breakingbadapi.com/api/characters/50")
                .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
                .and().rootPath("[0]").body("appearance.size()", Matchers.greaterThanOrEqualTo(2))
        .and().body("nickname", Matchers.equalToIgnoringCase("Don Juan"))
        .and().body("status", Matchers.equalToIgnoringCase("Deceased"));
    }

    @Test
    public void getReq3() {

        RestAssured.baseURI = "https://api.got.show";
        RestAssured.basePath = "api/map/characters/byId/5cc0743504e71a0010b852d9";
        given().header("Accept", ContentType.JSON).when().get()
                .then().log().ifStatusCodeIsEqualTo(200).assertThat().statusCode(200).contentType(ContentType.JSON)
                .rootPath("data")
                .body("books", Matchers.hasItem("A Storm of Swords")).body("dateOfBirth", Matchers.equalTo(283))
        .body("house", Matchers.is("House Stark")).body("male", Matchers.is(true))
        .body("male", Matchers.isA(Boolean.class));
    }

}

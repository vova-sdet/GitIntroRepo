package API.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredGet {

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet";
    }

    @Test
    public void getPet() {

        given().accept(ContentType.JSON).when().get("{id}", 81912).then().contentType(ContentType.JSON).statusCode(200).log().all();
    }

    @Test
    public void getPet2() {

        given().accept(ContentType.JSON).when().get("{id}", 81912).then().contentType(ContentType.JSON).statusCode(200).log().all();
    }

    @Test
    public void getPet3() {

        given().accept(ContentType.JSON).when().request("GET", "81912").then().statusCode(200);
    }

    @Test
    public void getPet4() {

        given().accept(ContentType.JSON).when().request("GET", "{id}", 110).then().statusCode(200).log().all();
    }
}

package API.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredDelete {

    @Test
    public void deletePet() {

        RestAssured.baseURI = "http://petstore.swagger.io/v2";
        RestAssured.basePath = "pet/";
        given().accept(ContentType.JSON).contentType(ContentType.JSON).when().delete("81912").then().statusCode(200)
                .contentType(ContentType.JSON).log().body();
    }
}

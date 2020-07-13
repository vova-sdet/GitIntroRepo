package API.restAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class RestAssuredAdvanced {

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

    public void adv() {

        Map<String, ?> map = response.as(Map.class);
        System.out.println(map.get("count"));
    }
}

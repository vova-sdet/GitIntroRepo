package API.restAssured;

import API.pojo.catFacts.AllPojo;
import API.pojo.catFacts.CatFactPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CatFactsGET {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://cat-fact.herokuapp.com";
        RestAssured.basePath = "facts";
    }

    @Test
    public void get25Fact() {

        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .get().then().statusCode(200).and().contentType(ContentType.JSON).extract().response();

        AllPojo parsedResponse = response.as(AllPojo.class);
        Map<String, String> nameObject = (Map<String, String>) parsedResponse.getAll().get(24).getUser().get("name");

        System.out.println(nameObject.get("first"));
        System.out.println(nameObject.get("last"));
        System.out.println(parsedResponse.getAll().get(24).get_id());
        System.out.println(parsedResponse.getAll().get(24).getText());


    }
}

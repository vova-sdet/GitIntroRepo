package API.restAssured;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredPut {

    @Test
    public void updatePet() {

        File updatePetPayload = new File("target/jsons/pet2.json");
        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(updatePetPayload)
                .when().post("https://petstore.swagger.io/v2/pet").
                then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().body(RestAssuredPost.NAME, Matchers.equalTo("Charlie"))
                .and().body(RestAssuredPost.STATUS, Matchers.is("cool guy")).log().all();
    }

    @Test
    public void updatePet2() {

        Map<String, Object> updatedPetPayload = new HashMap<>();
        updatedPetPayload.put(RestAssuredPost.NAME, "Charlie");
        updatedPetPayload.put("age", 7);
        updatedPetPayload.put(RestAssuredPost.STATUS, "available");
        updatedPetPayload.put("id", 81912);
        // updatedPetPayload.put("photoUrls", new ArrayList<String>());

        File updatePetPayload = new File("target/jsons/pet2.json");
        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(updatedPetPayload)
                .when().put("https://petstore.swagger.io/v2/pet").
                then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().body(RestAssuredPost.NAME, Matchers.equalTo(updatedPetPayload.get(RestAssuredPost.NAME)))
                .and().body(RestAssuredPost.STATUS, Matchers.is(updatedPetPayload.get(RestAssuredPost.STATUS))).log().all();
    }




}

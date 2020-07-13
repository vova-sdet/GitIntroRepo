package API.restAssured;

import API.pojo.PetPojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class RestAssuredPost {

    public static  final String NAME = "name";
    public static  final String STATUS = "status";

    ResponseSpecification responseSpecification;

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet";
        requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON).setContentType(ContentType.JSON).build();
        responseSpecification = new ResponseSpecBuilder().log(LogDetail.BODY).expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @Test
    public void createPet() {

        File petPayload = new File("target/jsons/pet2.json");
        given().spec(requestSpecification).body(petPayload).when().post()
        .then().spec(responseSpecification).body(NAME, Matchers.equalTo("Charlie"))
        .and().body("id", Matchers.is(81912));
    }

    @Test
    public void createPet2() {

        PetPojo petPojo = new PetPojo(9182, "hatiko", "sad boy");
        //petPojo.setId();
        given().spec(requestSpecification).body(petPojo).when().post()
        .then().assertThat().spec(responseSpecification).and().body(NAME, Matchers.is(petPojo.getName()))
        .and().body(STATUS, Matchers.is(petPojo.getStatus())).log().headers();
    }

    @Test
    public void createPet3() {

        Map<String, Object> petPayload = new HashMap<>();
        petPayload.put(NAME, "name");
        petPayload.put("age", 7);
        petPayload.put(STATUS, "old boy");
        petPayload.put("id", 1012);
        petPayload.put("photoUrls", new ArrayList<String>());

        given().spec(requestSpecification).body(petPayload).when().post()
                .then().assertThat().spec(responseSpecification).
                and().body(NAME, Matchers.is(petPayload.get(NAME)));
    }
}
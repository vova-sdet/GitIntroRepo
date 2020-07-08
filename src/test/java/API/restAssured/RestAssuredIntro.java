package API.restAssured;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredIntro {

    @Test
    public void rest1() {

        // https://swapi.dev/api/planets/1
        given().header("Content-Type", ContentType.JSON).header("Accept", ContentType.JSON).when().get("https://swapi.dev/api/planets/1").
                then().statusCode(200).and().contentType(ContentType.JSON);
    }

    @Test
    public void rest2() {

        given().header("Accept", ContentType.JSON).when().get("https://swapi.dev/api/planets/1").then().statusCode(200).
                and().contentType(ContentType.JSON).and().assertThat().body("name", equalTo("Tatooine"));
    }

    @Test
    public void rest3() {

        given().header("Accept", ContentType.JSON).when().get("https://swapi.dev/api/planets").then().log().all().assertThat().statusCode(200).
                contentType(ContentType.JSON).and().body("results[0].gravity", Matchers.is("1 standard")).
                and().body("results[0].terrain", Matchers.isA(String.class));
    }
}

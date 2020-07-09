package API.restAssured;

import API.com.Practice.pojoDeserialize.BBCharPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredDeserialization {

    @Before
    public void setup() {

        RestAssured.baseURI = "https://breakingbadapi.com";
        RestAssured.basePath = "api/characters";
    }


    @Test
    public void getReq1() {

        List<BBCharPojo> bbCharPojos = given().header("Accept", ContentType.JSON).when().get("50").then().
                statusCode(200).and().contentType(ContentType.JSON).extract().as(new TypeRef<List<BBCharPojo>>() {});

        Assert.assertEquals("Juan Bolsa", bbCharPojos.get(0).getName());
    }

    @Test
    public void getReq2() {

        List<BBCharPojo> bbCharPojos = given().header("Accept", ContentType.JSON).when().get("35").then().
                statusCode(200).and().contentType(ContentType.JSON).extract().as(new TypeRef<List<BBCharPojo>>() {
        });

        Assert.assertEquals("Dr. Delcavoli", bbCharPojos.get(0).getName());
        Assert.assertEquals(2, bbCharPojos.get(0).getAppearance().size());
        Assert.assertEquals("Unknown", bbCharPojos.get(0).getBirthday());
    }
}
